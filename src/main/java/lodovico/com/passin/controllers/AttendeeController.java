package lodovico.com.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendees")
public class AttendeeController {
    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<String>getAttendeeBadge(@PathVariable String attendeeId) {
        return ResponseEntity.ok("sucesso!");
    }
}
