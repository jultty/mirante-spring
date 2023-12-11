package mirante.api;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Exercise {
  
  @Id @JsonProperty("id")
  private String id;

  @OneToMany
  @JoinColumn(name = "exercise_option")
  private Set<Option> options;

  public Exercise() {}

  public Exercise(String id) {
    this.id = id;
  }

  public void addOption(Option option) {
    options.add(option);
  }
}

