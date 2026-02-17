package ticket.exception;

public class InvalidPasswordException extends AuthException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
