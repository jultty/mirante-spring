package mirante.api.exercise;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import mirante.api.exercise.option.Option;
import mirante.api.exercise.set.ExerciseSet;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Exercise {

  @Id @JsonProperty("id") private String id;
  @JsonProperty("instruction") private String instruction;

  @JsonManagedReference
  @OneToMany @JoinColumn(name = "exercise_option")
  private Set<Option> options;

  @JsonProperty("set")
  @ManyToOne @JoinColumn(name = "exercise_set", referencedColumnName = "id")
  private ExerciseSet set;

  public Exercise() {}

  public Exercise(String id) {
    this.id = id;
  }

  public Exercise(String id, String instruction) {
    this.id = id;
    this.instruction = instruction;
  }

  public void addOption(Option option) {
    if (option.getExerciseId().equals(id)) {
      options.add(option);
    }
  }

  public String getId() { return id; }
  public String getInstruction() { return instruction; }
  public String getSetId() { return set.getId(); }
  public Set<Option> getOptions() { return options; }
}

