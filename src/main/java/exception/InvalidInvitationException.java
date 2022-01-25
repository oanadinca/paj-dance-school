package exception;

public class InvalidInvitationException extends RuntimeException {
    public InvalidInvitationException() {
        super("The invitation request is invalid.");
    }
}
