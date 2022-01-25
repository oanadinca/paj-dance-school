package model;

import lombok.Data;

import java.util.Date;

@Data
public class Invitation {
    String name;
    String surname;
    DanceClassType danceClassType;

    private Date expiryDate;
}
