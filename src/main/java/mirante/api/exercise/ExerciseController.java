package mirante.api.exercise;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
class ExerciseController {
  private final ExerciseRepository repository;

  ExerciseController(ExerciseRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/exercise")
  List<Exercise> all() {
    return repository.findAll();
  }

  @PostMapping("/exercise")
  @ResponseStatus(HttpStatus.CREATED)
  Exercise newExercise(@RequestBody Exercise newExercise) {
    return repository.save(newExercise);
  }

  @GetMapping("/exercise/{id}")
  Exercise one(@PathVariable String id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Exercise with id ${id} not found")
          );
  }

  @DeleteMapping("/exercise/{id}")
  void deleteExercise(@PathVariable String id) {
    repository.deleteById(id);
  }
}
