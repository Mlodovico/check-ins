package lodovico.com.passin.domain.attendee.exception;

public class AttendeeAlreadyRegisteredException extends RuntimeException {
    public AttendeeAlreadyRegisteredException(String message) {
        super(message);
    }
}
