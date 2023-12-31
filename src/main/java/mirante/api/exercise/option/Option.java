package mirante.api.exercise.option;

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

  @JsonProperty("exercise_id")
  @ManyToOne @JoinColumn(name = "exercise_option", referencedColumnName = "id")
  private Exercise exercise;

  public Option() {}

  public Option(String id, Integer place, String content, String exercise_id, Boolean correct) {
    this.id = id;
    this.place = place;
    this.content = content;
    this.correct = correct;
  }
}
