package lodovico.com.passin.domain.checkin.exception;

public class AttendeeAlreadyCheckedIn extends RuntimeException {
    public AttendeeAlreadyCheckedIn(String message) {
        super(message);
    }
}
