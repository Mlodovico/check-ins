package lodovico.com.passin.dto.attendee;

import lombok.Getter;

import java.util.List;

@Getter
public class AttendeeListResponseDTO {
    List<AttendeeDetails> attendees;
}
