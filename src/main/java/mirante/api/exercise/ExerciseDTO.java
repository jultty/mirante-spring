package mirante.api.exercise;

import java.util.Set;
import mirante.api.exercise.option.Option;

public class ExerciseDTO {
  public String id;
  public String instruction;
  public String set;
  public Set<Option> options;
}
