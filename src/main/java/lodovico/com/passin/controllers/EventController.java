package lodovico.com.passin.controllers;

import lodovico.com.passin.dto.attendee.AttendeeListResponseDTO;
import lodovico.com.passin.dto.event.EventIdDTO;
import lodovico.com.passin.dto.event.EventRequestDTO;
import lodovico.com.passin.dto.event.EventResponseDTO;
import lodovico.com.passin.services.AttendeeService;
import lodovico.com.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private EventService eventService;
    private AttendeeService attendeeService;
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String eventId) {
        EventResponseDTO event = this.eventService.getEventDetail(eventId);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        EventIdDTO eventIdDTO =  this.eventService.createEvent(body);
        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();
        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeeListResponseDTO> getEventAttendees(@PathVariable String id) {
        AttendeeListResponseDTO event = this.attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(event);
    }
}
