package mirante.api.exercise;

import mirante.api.exercise.option.OptionDTO;

import java.util.Set;

public class ExerciseDTO {
  public String id;
  public String instruction;
  public String set;
  public Set<OptionDTO> options;
}
