package mirante.api.event;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
class EventController {
  private final EventRepository repository;

  EventController(EventRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/event")
  List<Event> all() {
    return repository.findAll();
  }

  @PostMapping("/event")
  @ResponseStatus(HttpStatus.CREATED)
  Event newEvent(@RequestBody Event newEvent) {
    return repository.save(newEvent);
  }

  @GetMapping("/event/{id}")
  Event one(@PathVariable String id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Event with id ${id} not found")
          );
  }
}
