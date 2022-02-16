package com.app.service;

import com.app.exception.DuplicateMemberException;
import com.app.exception.InvalidDanceClassException;
import com.app.model.DanceClass;
import com.app.model.DanceStudent;
import com.app.model.DanceType;
import com.app.model.SMS;
import com.app.repo.DanceClassRepository;
import com.app.repo.DanceStudentRepository;
import com.app.utils.MemberRegistrationListener;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DanceSchoolService {
    private final List<MemberRegistrationListener> listeners = new ArrayList<>();

    private final DanceClassRepository danceClassRepository;
    private final DanceStudentRepository danceStudentRepository;

    private final SMSService smsService;

    private int printedMembers = 0;
    private int messagedMembers = 0;

    public DanceSchoolService(DanceClassRepository danceClassRepository,
                              DanceStudentRepository danceStudentRepository,
                              SMSService smsService) {
        this.danceClassRepository = danceClassRepository;
        this.danceStudentRepository = danceStudentRepository;
        this.smsService = smsService;
        listeners.add(member -> {
            System.out.println("Member added: " + member.getName() + " " + member.getSurname());
            printedMembers++;
        });

        listeners.add(member -> {
            System.out.println("SMS for member " + member.getName() + " " + member.getSurname() + " to be sent");
            messagedMembers++;
        });
    }

    public int addStudent(final DanceStudent member, final DanceType danceType) throws DuplicateMemberException {
        List<DanceClass> danceClasses = danceClassRepository.findAll();
        danceClasses.stream()
                .filter(danceClass1 -> danceClass1.getDanceClassType().name().equals(danceType.name().toUpperCase()))
                .findAny().ifPresentOrElse(danceClass1 -> {
                }, () -> {
                    throw new InvalidDanceClassException();
                }
        );


        List<DanceStudent> danceStudents = danceStudentRepository.findAll();
        danceStudents.stream().filter(danceStudent ->
                danceStudent.getName().equals(member.getName()) && danceStudent.getSurname().equals(member.getSurname())
                        && !danceStudent.getDanceClasses().stream().noneMatch(danceClass1 -> danceClass1.getDanceClassType().name().equals(danceType.name().toUpperCase())))
                .findAny().ifPresentOrElse(danceStudent -> {
                    throw new DuplicateMemberException();
                },
                () -> {
                    DanceClass danceClass = danceClassRepository.findByDanceType(danceType);
                    DanceStudent danceStudent = null;
                    for (DanceStudent s : danceStudents) {
                        if (s.getName().equals(member.getName()) && s.getSurname().equals(member.getSurname())) {
                            danceStudent = s;
                        }
                    }
                    if (danceStudent != null) {
                        danceStudent.addDanceClass(danceClass);
                        danceStudentRepository.save(danceStudent);
                    } else {
                        member.addDanceClass(danceClassRepository.findByDanceType(danceType));
                        danceStudentRepository.save(member);
                    }
                    notify(member);
                }
        );
        return 1;
    }

    public List<DanceStudent> getClassStudents(DanceType danceType) {
        DanceClass danceClass = danceClassRepository.findByDanceType(danceType);
        List<DanceStudent> danceStudents = danceStudentRepository.findAll();
        return danceStudents.stream().filter(danceStudent -> {
                List<DanceClass> danceClasses = danceStudent.getDanceClasses();
                for (DanceClass c: danceClasses) {
                    if (c.getDanceClassType().name().equals(danceClass.getDanceClassType().name())) {
                        return true;
                    }
                }
                return false;
        })
                .collect(Collectors.toList());
    }

    public Long getStudentDanceClassPaymentAmount(String name, String surname) {
        Long result = 0L;
        Optional<DanceStudent> student = danceStudentRepository.getByNameAndSurname(name, surname);
        if (student.isPresent()) {
            return student.get().getDanceClasses().stream().mapToLong(DanceClass::getPrice).sum();
        }
        return result;
    }

    public int getPrintedMembers() {
        return printedMembers;
    }

    public int getMessagedMembers() {
        return messagedMembers;
    }

    private void notify(DanceStudent member) {
        for (MemberRegistrationListener listener : listeners) {
            listener.onMemberAdded(member);
            smsService.sendNotificationSMS(new SMS(
                    member, RandomString.make(10)
            ));
        }
    }
}
