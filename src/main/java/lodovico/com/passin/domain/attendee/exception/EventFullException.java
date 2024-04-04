package lodovico.com.passin.domain.attendee.exception;

public class EventFullException extends RuntimeException{
    public EventFullException(String message) {
        super(message);
    }
}
