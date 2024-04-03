package lodovico.com.passin.repositories;

import lodovico.com.passin.domain.attendee.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {

    List<Attendee> findByEventId(String eventId);
}
