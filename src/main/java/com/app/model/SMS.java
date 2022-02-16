package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SMS {

    private DanceStudent member;
    private String uniqueCode;
}
