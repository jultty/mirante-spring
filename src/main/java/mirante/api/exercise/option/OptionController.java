package mirante.api.exercise.option;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
class OptionController {
  private final OptionRepository repository;

  OptionController(OptionRepository repository) {
    this.repository = repository;
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/option")
  List<Option> all() {
    return repository.findAll();
  }

  @PostMapping("/option")
  @ResponseStatus(HttpStatus.CREATED)
  Option newOption(@RequestBody Option newOption) {
    return repository.save(newOption);
  }

  @GetMapping("/option/{id}")
  Option one(@PathVariable String id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Option with id ${id} not found")
          );
  }

  @DeleteMapping("/option/{id}")
  void deleteOption(@PathVariable String id) {
    repository.deleteById(id);
  }
}
