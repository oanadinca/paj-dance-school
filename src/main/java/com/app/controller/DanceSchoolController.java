package com.app.controller;

import com.app.dto.DanceClassHttpResponse;
import com.app.dto.DanceStudentHttpRequest;
import com.app.dto.DanceStudentHttpResponse;
import com.app.model.DanceClass;
import com.app.model.DanceStudent;
import com.app.model.DanceType;
import com.app.service.DanceSchoolService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dance/class")
public class DanceSchoolController {

    private final DanceSchoolService danceSchoolService;

    public DanceSchoolController(DanceSchoolService danceSchoolService) {
        this.danceSchoolService = danceSchoolService;
    }

    @PostMapping
    public ResponseEntity<DanceClassHttpResponse> addStudent(@RequestBody DanceStudentHttpRequest student) {
        try {
            danceSchoolService.addStudent(student.getDanceStudent(), student.getDanceType());
            return ResponseEntity.ok(new DanceClassHttpResponse("New Student added", "201", null));
        } catch (Exception e) {
            return ResponseEntity.ok((new DanceClassHttpResponse(e.getMessage(), "400", null)));
        }
    }

    @GetMapping(path = "/type")
    public ResponseEntity<DanceStudentHttpResponse> getDanceStudents(@RequestParam(name = "type") DanceType danceClassType) {
        List<DanceStudent> result = danceSchoolService.getClassStudents(danceClassType);
        return ResponseEntity.ok(new DanceStudentHttpResponse(null, null, result));
    }

    @GetMapping(path = "/price")
    public ResponseEntity<String> getStudentDanceClassPaymentAmount(@Param("name") String name, @Param("surname") String surname) {
        return ResponseEntity.ok("The total amount spent by " + name + " "+ surname+ " is "+ danceSchoolService.getStudentDanceClassPaymentAmount(name, surname));
    }
}
