package mirante.api.result;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import mirante.api.exercise.ExerciseSet;

@Entity
public class Result {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private Timestamp time;

  @ManyToOne @JoinColumn(name = "result_set", referencedColumnName = "id")
  private ExerciseSet set;

  public Result() {}

  public Result(Timestamp time, ExerciseSet set) {
    this.time = time;
    this.set = set;
  }
}

