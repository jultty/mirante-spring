package mirante.api.exercise.option;

import com.fasterxml.jackson.annotation.JsonBackReference;
import mirante.api.exercise.Exercise;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Option {
  @Id @JsonProperty("id") private String id;
  @JsonProperty("content") private String content;
  @JsonProperty("place") private Integer place;
  @JsonProperty("correct") private Boolean correct;

  @JsonBackReference
  @JsonProperty("exercise_id")
  @ManyToOne @JoinColumn(name = "exercise_option", referencedColumnName = "id")
  private Exercise exercise;

  public Option() {}

  public String getId() { return id; }
  public String getContent() { return content; }
  public Integer getPlace() { return place; }
  public Boolean getCorrect() { return correct; }
  public Exercise getExercise() { return exercise; }
  public String getExerciseId() { return exercise.getId(); }
}
