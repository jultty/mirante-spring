package mirante.api;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
class ExerciseSetController {
  private final ExerciseSetRepository repository;

  ExerciseSetController(ExerciseSetRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/exercise-set")
  List<ExerciseSet> all() {
    return repository.findAll();
  }

  @PostMapping("/exercise-set")
  @ResponseStatus(HttpStatus.CREATED)
  ExerciseSet newExerciseSet(@RequestBody ExerciseSet newExerciseSet) {
    return repository.save(newExerciseSet);
  }

  @GetMapping("/exercise-set/{id}")
  ExerciseSet one(@PathVariable String id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "ExerciseSet with id ${id} not found")
          );
  }

  @DeleteMapping("/exercise-set/{id}")
  void deleteExerciseSet(@PathVariable String id) {
    repository.deleteById(id);
  }
}
