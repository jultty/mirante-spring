package mirante.api.result;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import mirante.api.exercise.Exercise;

@Entity
public class Result {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private Timestamp time;

  @ManyToOne @JoinColumn(name = "result_exercise", referencedColumnName = "id")
  private Exercise exercise;

  public Result() {}

  public Result(Timestamp time, Exercise exercise) {
    this.time = time;
    this.exercise = exercise;
  }
}

