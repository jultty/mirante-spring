package mirante.api;

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

  @OneToMany
  @JoinColumn(name = "exercise_option")
  private Set<Option> options;

  @JsonProperty("set_id")
  @ManyToOne @JoinColumn(name = "exercise_set", referencedColumnName = "id")
  private ExerciseSet set;

  public Exercise() {}

  public Exercise(String id) {
    this.id = id;
  }

  public void addOption(Option option) {
    options.add(option);
  }
}

