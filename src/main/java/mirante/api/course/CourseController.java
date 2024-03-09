package mirante.api.course;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
class CourseController {
  private final CourseRepository repository;

  CourseController(CourseRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/course")
  List<Course> all() {
    return repository.findAll();
  }

  @PostMapping("/course")
  @ResponseStatus(HttpStatus.CREATED)
  Course newCourse(@RequestBody Course newCourse) {
    return repository.save(newCourse);
  }

  @GetMapping("/course/{id}")
  Course one(@PathVariable String id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Course with id ${id} not found")
          );
  }

  @DeleteMapping("/course/{id}")
  void deleteCourse(@PathVariable String id) {
    repository.deleteById(id);
  }
}
