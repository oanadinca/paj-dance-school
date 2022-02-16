package com.app.dto;

import com.app.model.DanceStudent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DanceStudentHttpResponse {
    String message;
    String code;
    List<DanceStudent> danceStudentList;
}
