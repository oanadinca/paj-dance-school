package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Gender {
    MALE("Mr."), FEMALE("Ms.");

    @Getter
    private final String greeting;
}
