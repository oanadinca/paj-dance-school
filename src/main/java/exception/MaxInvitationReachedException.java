package exception;

public class MaxInvitationReachedException extends RuntimeException {
    public MaxInvitationReachedException() {
        super("The member ran out of invitations.");
    }
}
