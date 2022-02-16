package com.app.dto;

import com.app.model.DanceStudent;
import com.app.model.DanceType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DanceStudentHttpRequest {
    DanceStudent danceStudent;
    DanceType danceType;
}
