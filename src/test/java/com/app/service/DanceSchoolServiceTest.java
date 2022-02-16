package com.app.service;

import com.app.exception.DuplicateMemberException;
import com.app.exception.InvalidDanceClassException;
import com.app.model.DanceClass;
import com.app.model.DanceStudent;
import com.app.model.DanceType;
import com.app.model.Gender;
import com.app.repo.DanceClassRepository;
import com.app.repo.DanceStudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DanceSchoolServiceTest {

    @Mock
    private DanceClassRepository danceClassRepository;
    @Mock
    private DanceStudentRepository danceStudentRepository;
    @InjectMocks
    private DanceSchoolService danceSchoolService;

    private List<DanceStudent> students = new ArrayList<>();

    private List<DanceClass> classes = new ArrayList<>();

    @BeforeEach
    public void setup() {
        DanceStudent student = new DanceStudent(
                "Ana",
                "Maria",
                Gender.FEMALE);
        students.add(student);

        DanceStudent student2 = new DanceStudent(
                "Ioana",
                "Maria",
                Gender.FEMALE);
        students.add(student2);

        DanceClass danceClass = new DanceClass(
                DanceType.SALSA, 100L, students
        );
        classes.add(danceClass);
    }

    @Test
    public void addStudent_duplicateError() throws DuplicateMemberException {

        classes.add(new DanceClass(DanceType.BACHATA, 100L, students));
        students.get(0).addDanceClass(classes.get(1));
        Mockito.when(danceClassRepository.findAll()).thenReturn(classes);
        Mockito.when(danceStudentRepository.findAll()).thenReturn(students);

        DuplicateMemberException thrown = Assertions.assertThrows(DuplicateMemberException.class,
                ()-> danceSchoolService.addStudent(students.get(0), DanceType.BACHATA)
        );

        Assertions.assertEquals(thrown.getMessage(), "A member with the same name and surname in this dance class already exists.");

    }

    @Test
    public void addStudent_invalidDanceClass() {

        Mockito.when(danceClassRepository.findAll()).thenReturn(classes);

        InvalidDanceClassException thrown = Assertions.assertThrows(InvalidDanceClassException.class,
                ()-> danceSchoolService.addStudent(students.get(0), DanceType.BACHATA)
                );

        Assertions.assertEquals(thrown.getMessage(), "The dance class is invalid.");

    }

    @Test
    public void getClassStudents_filteringDoneRight() {
        classes.add(new DanceClass(DanceType.BACHATA, 100L, students));
        students.get(0).addDanceClass(classes.get(1));
        Mockito.when(danceClassRepository.findByDanceType(DanceType.BACHATA)).thenReturn(classes.get(1));
        Mockito.when(danceStudentRepository.findAll()).thenReturn(students);

        List<DanceStudent> result = danceSchoolService.getClassStudents(DanceType.BACHATA);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(students.get(0).getName(), result.get(0).getName());
    }

    @Test
    public void getStudentDanceClassPaymentAmount_SumDoneRight() {
        classes.add(new DanceClass(DanceType.BACHATA, 100L, students));
        students.get(0).addDanceClass(classes.get(0));
        students.get(0).addDanceClass(classes.get(1));
        Mockito.when(danceStudentRepository.getByNameAndSurname("Ana", "Maria")).thenReturn(java.util.Optional.ofNullable(students.get(0)));

        Long result = danceSchoolService.getStudentDanceClassPaymentAmount(students.get(0).getName(), students.get(0).getSurname());
        Assertions.assertEquals(200L, result);
    }

    @Test
    public void getStudentDanceClassPaymentAmount_SumDoneWrong() {
        Mockito.when(danceStudentRepository.getByNameAndSurname("X", "Maria")).thenReturn(Optional.empty());

        Long result = danceSchoolService.getStudentDanceClassPaymentAmount("X", "Maria");
        Assertions.assertEquals(0L, result);
    }
}