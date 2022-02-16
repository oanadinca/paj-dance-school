package com.app;

import com.app.controller.DanceSchoolController;
import com.app.dto.DanceStudentHttpRequest;
import com.app.model.DanceStudent;
import com.app.model.DanceType;
import com.app.model.Gender;
import com.app.service.DanceSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private DanceSchoolController danceSchoolController;

    @Autowired
    private DanceSchoolService danceSchoolService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
//        addStudent("Mihnea", "Mih", Gender.MALE, DanceType.BACHATA);
//        addStudent("Mihnea", "Mih", Gender.MALE, DanceType.SALSA);
//        addStudent("Vale", "Dragu", Gender.FEMALE, DanceType.SALSA);
//        addStudent("Alex", "Ion", Gender.MALE, DanceType.SALSA);
//        addStudent("Susie", "Q", Gender.FEMALE, DanceType.KIZOMBA);
//        addStudent("Bibi", "Ann", Gender.FEMALE, DanceType.SALSA);
//        addStudent("Oana", "Dinca", Gender.FEMALE, DanceType.BACHATA);


        System.out.println(danceSchoolController.getDanceStudents(DanceType.SALSA));
        System.out.println(danceSchoolController.getStudentDanceClassPaymentAmount("Mihnea", "Mih"));

        System.out.println("Total printed members" + danceSchoolService.getPrintedMembers());
        System.out.println("Total messaged members" + danceSchoolService.getMessagedMembers());
    }

    private void addStudent(String name, String surname, Gender gender, DanceType danceType) {
        DanceStudent student = new DanceStudent(
                name,
                surname,
                gender);
        DanceStudentHttpRequest studentHttpRequest = new DanceStudentHttpRequest(student, danceType);
        danceSchoolController.addStudent(studentHttpRequest);
    }
}
