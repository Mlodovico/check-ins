package lodovico.com.passin.controllers;

import lodovico.com.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private EventService service;
    @GetMapping("/{id}")
    public ResponseEntity<String>getEvent(@PathVariable String eventId) {
        this.service.getEventDetail(eventId);
        return ResponseEntity.ok("sucesso");
    }
}
