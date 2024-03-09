package mirante.api.exercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mirante.api.exercise.option.OptionDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
class ExerciseController {
  private final ExerciseRepository repository;

  ExerciseController(ExerciseRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/exercise")
  List<ExerciseDTO> getExercises() {
    List<Exercise> exercises = repository.findAll();
    List<ExerciseDTO> exerciseDTOs = new ArrayList<ExerciseDTO>();

    exercises.forEach(e -> {
      ExerciseDTO dto = new ExerciseDTO();
      dto.id = e.getId();
      dto.instruction = e.getInstruction();
      dto.set = e.getSetId();
      dto.options = new HashSet<OptionDTO>();
      e.getOptions().forEach(o -> {
        OptionDTO optionDTO = new OptionDTO();
        optionDTO.id = o.getId();
        optionDTO.content = o.getContent();
        optionDTO.place = o.getPlace();
        optionDTO.correct = o.getCorrect();
        optionDTO.exercise = o.getExerciseId();
        dto.options.add(optionDTO);
      });
      exerciseDTOs.add(dto);
    });

    return exerciseDTOs;
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
