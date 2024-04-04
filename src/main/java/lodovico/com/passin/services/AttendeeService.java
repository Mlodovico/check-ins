package lodovico.com.passin.services;

import lodovico.com.passin.domain.attendee.Attendee;
import lodovico.com.passin.domain.checkin.CheckIn;
import lodovico.com.passin.dto.attendee.AttendeeDetails;
import lodovico.com.passin.dto.attendee.AttendeeListResponseDTO;
import lodovico.com.passin.repositories.AttendeeRepository;
import lodovico.com.passin.repositories.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private AttendeeRepository attendeeRepository;
    private CheckInRepository checkInRepository;
    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        List<Attendee> attendeesList = this.attendeeRepository.findByEventId(eventId);
        return attendeesList;
    }

    public AttendeeListResponseDTO getEventsAttendee(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetails = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkInAt)
        }).toList();
    }
}
