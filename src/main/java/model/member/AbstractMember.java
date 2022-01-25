package model.member;

import lombok.Data;
import model.Gender;

import java.util.Date;

@Data
public abstract class AbstractMember {

    private String name;
    private String surname;
    private Gender gender;

    private Long price;
    private Date startDate;
    private Date endDate;
}
