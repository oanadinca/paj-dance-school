package com.app.service;

import com.app.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SMSServiceTest {

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
    public void testSendSMS() throws InterruptedException {

        SMSService emailService = new SMSService();
        for (int i = 0; i < 5; i++) {
            try {
                emailService.sendNotificationSMS(
                        new SMS(students.get(0), "test")
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

//            if (i == 2) {
//                emailService.close();
//            }

            Thread.sleep(1000);
        }

        assertEquals(5, emailService.getSentSMS());
    }
}