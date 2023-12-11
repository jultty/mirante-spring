package mirante.api;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

@Entity
public class ExerciseSet {
  
  @Id 
  private String id;

  @OneToMany
  @JoinColumn(name = "set_exercise")
  private Set<Exercise> exercises;

  public ExerciseSet() {}

  public ExerciseSet(String id) {
    this.id = id;
  }
}

