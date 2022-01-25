package model.member;

import lombok.Data;
import model.DanceClassType;

@Data
public class BasicMember extends AbstractMember {
    DanceClassType danceClass;
}
