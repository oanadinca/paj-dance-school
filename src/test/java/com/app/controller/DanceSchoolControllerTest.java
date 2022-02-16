package com.app.controller;

import com.app.dto.DanceClassHttpResponse;
import com.app.dto.DanceStudentHttpRequest;
import com.app.dto.DanceStudentHttpResponse;
import com.app.model.DanceStudent;
import com.app.model.DanceType;
import com.app.model.Gender;
import com.app.service.DanceSchoolService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DanceSchoolControllerTest {

    @Mock
    private DanceSchoolService danceSchoolService;

    @InjectMocks
    private DanceSchoolController danceSchoolController;

    @Test
    public void validate_addStudent() {
        DanceStudent student = new DanceStudent(
                "Ana",
                "Maria",
                Gender.FEMALE);
        DanceStudentHttpRequest studentHttpRequest = new DanceStudentHttpRequest(student, DanceType.BACHATA);

        ResponseEntity<DanceStudentHttpResponse> responseEntity = new ResponseEntity(new DanceClassHttpResponse("New Student added", "201", null), HttpStatus.OK);

        when(danceSchoolService.addStudent(studentHttpRequest.getDanceStudent(), studentHttpRequest.getDanceType())).thenReturn(1);
        ResponseEntity<DanceClassHttpResponse> result = danceSchoolController.addStudent(studentHttpRequest);
        assertEquals(responseEntity.getStatusCode(), result.getStatusCode());
        assertEquals(responseEntity.getBody(), result.getBody());
    }
}