package mirante.api.result;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
class ResultController {
  private final ResultRepository repository;

  ResultController(ResultRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/result")
  List<Result> all() {
    return repository.findAll();
  }

  @PostMapping("/result")
  @ResponseStatus(HttpStatus.CREATED)
  Result newResult(@RequestBody Result newResult) {
    return repository.save(newResult);
  }

  @GetMapping("/result/{id}")
  Result one(@PathVariable String id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Result with id ${id} not found")
          );
  }
}
