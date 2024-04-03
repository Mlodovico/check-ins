package lodovico.com.passin.services;

import lodovico.com.passin.domain.attendee.Attendee;
import lodovico.com.passin.domain.event.Event;
import lodovico.com.passin.dto.event.EventResponseDTO;
import lodovico.com.passin.repositories.AttendeeRepository;
import lodovico.com.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private EventRepository eventRepository;
    private AttendeeRepository attendeeRepository;

    public EventResponseDTO getEventDetail(String eventId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found with ID:" + eventId));
        List<Attendee> attendeeList = this.attendeeRepository.findByEventId(eventId);
        return new EventResponseDTO(event, attendeeList.size());
    }
}
