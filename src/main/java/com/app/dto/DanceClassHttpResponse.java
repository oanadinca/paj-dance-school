package com.app.dto;

import com.app.model.DanceClass;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DanceClassHttpResponse {
    String message;
    String code;
    DanceClass danceClass;
}
