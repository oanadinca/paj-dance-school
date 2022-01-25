package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DanceClassType {
    SALSA("Salsa"),
    BACHATA("bachata"),
    KIZOMBA("Kizomba");

    @Getter
    private final String type;
}
