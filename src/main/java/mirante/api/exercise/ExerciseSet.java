package mirante.api.exercise;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ExerciseSet {
  
  @Id @JsonProperty("id") private String id;
  @JsonProperty("name") private String name;

  @OneToMany @JoinColumn(name = "exercise_set")
  private Set<Exercise> exercises;

  public ExerciseSet() {}

  public ExerciseSet(String id) {
    this.id = id;
    this.name = "";
  }

  public void setName(String name) {
    this.name = name;
  }

  public ExerciseSet(String id, String name) {
    this.id = id;
    this.name = name;
  }
}

