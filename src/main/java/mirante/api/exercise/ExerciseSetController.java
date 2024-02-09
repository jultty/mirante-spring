package mirante.api.exercise;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
class ExerciseSetController {
  private final ExerciseSetRepository repository;

  ExerciseSetController(ExerciseSetRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/set")
  List<ExerciseSet> all() {
    return repository.findAll();
  }

  @PostMapping("/set")
  @ResponseStatus(HttpStatus.CREATED)
  ExerciseSet newExerciseSet(@RequestBody ExerciseSet newExerciseSet) {
    return repository.save(newExerciseSet);
  }

  @GetMapping("/set/{id}")
  ExerciseSet one(@PathVariable String id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Exercise set with id ${id} not found")
          );
  }

  @DeleteMapping("/set/{id}")
  void deleteExerciseSet(@PathVariable String id) {
    repository.deleteById(id);
  }
}
