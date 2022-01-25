package model.member;

import lombok.Data;
import model.DanceClassType;
import model.Invitation;

import java.util.List;

@Data
public class FullMember extends AbstractMember {
    List<DanceClassType> danceClasses;
    Invitation friendInvitation;
}
