package lodovico.com.passin.services;

import lodovico.com.passin.domain.attendee.Attendee;
import lodovico.com.passin.domain.attendee.exception.AttendeeAlreadyRegisteredException;
import lodovico.com.passin.domain.attendee.exception.AttendeeNotFoundException;
import lodovico.com.passin.domain.checkin.CheckIn;
import lodovico.com.passin.dto.attendee.AttendeeBadgeResponseDTO;
import lodovico.com.passin.dto.attendee.AttendeeDetails;
import lodovico.com.passin.dto.attendee.AttendeeListResponseDTO;
import lodovico.com.passin.dto.attendee.AttendeeBadgeDTO;
import lodovico.com.passin.repositories.AttendeeRepository;
import lodovico.com.passin.repositories.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckInService checkInService;

    private Attendee getAttendee(String attendeeId) {
        return this.attendeeRepository.findById(attendeeId).orElseThrow(() -> new AttendeeNotFoundException("Attendee not found with ID: " + attendeeId));
    }

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        List<Attendee> attendeesList = this.attendeeRepository.findByEventId(eventId);
        return attendeesList;
    }

    public AttendeeListResponseDTO getEventsAttendee(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetails = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkInService.getCheckIn(attendee.getId());
            LocalDateTime checkInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkInAt)
        }).toList();

        return new AttendeeListResponseDTO(attendeeDetails);
    }

    public Attendee registerAttendee(Attendee newAttendee) {
        this.attendeeRepository.save(newAttendee);
        return newAttendee;
    }

    public AttendeeBadgeResponseDTO getAttendeeBadge(String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        var uri = uriComponentsBuilder.path("/attendees/{attendeeId/check-in").buildAndExpand(attendeeId).toUri().toString();

        Attendee attendee = this.getAttendee(attendeeId);
        AttendeeBadgeDTO attendeeBadgeDTO = new AttendeeBadgeDTO(attendee.getName(), attendee.getEmail(), uri, attendee.getEvent().getId());

        return new AttendeeBadgeResponseDTO(attendeeBadgeDTO);
    }

    public void verifyAttendeeSubscription(String email, String eventId) {
        Optional<Attendee> isAttendeeRegistered = this.attendeeRepository.findByEventIdAndEmail(email, eventId);

        if (isAttendeeRegistered.isPresent()) throw new AttendeeAlreadyRegisteredException("Attendee already registered on this event!")
    }
    
    public void checkInAttendee(String attendeeId) {
        Attendee attendee = this.getAttendee(attendeeId);

        this.checkInService.registerCheckIn(attendee);
    }
}
