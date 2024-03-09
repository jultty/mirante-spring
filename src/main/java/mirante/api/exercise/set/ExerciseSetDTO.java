package mirante.api.exercise.set;

import java.util.Set;

import mirante.api.exercise.Exercise;
import mirante.api.exercise.ExerciseDTO;

public class ExerciseSetDTO {
    public String id;
    public String name;
    public Access access;
    public Set<ExerciseDTO> exercises;
}
