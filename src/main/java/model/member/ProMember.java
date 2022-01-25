package model.member;

import lombok.Data;
import model.DanceClassType;
import model.Invitation;

import java.util.List;
import java.util.Map;

@Data
public class ProMember extends AbstractMember {
    List<DanceClassType> danceClasses;
    Map<Invitation, DanceClassType> friendInvitations;
}
