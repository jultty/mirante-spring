package mirante.api.exercise;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonProperty;

import mirante.api.course.Course;

@Entity
public class ExerciseSet {
  
  @Id @JsonProperty("id") private String id;
  @JsonProperty("name") private String name;
  Access access;

  @ManyToMany @JoinColumn(name = "set_course")
  private Course course;

  @OneToMany @JoinColumn(name = "exercise_set")
  private Set<Exercise> exercises;

  public ExerciseSet() {}

  public ExerciseSet(String id) {
    this.id = id;
    this.name = "";
  }

  public ExerciseSet(String id, String name, Course course) {
    this.id = id;
    this.name = name;
    this.course = course;
    this.access = Access.PRIVATE;
  }

  public void setId(String id) { this.id = id; }
  public String getId() { return id; }
  public void setName(String name) { this.name = name; }
  public String getName() { return name; }
  public void setCourse(Course course) { this.course = course; }
  public Course getCourse() { return course; }
  public void setAccess(Access access) { this.access = access; }
  public Access getAccess() { return access; }
}

enum Access {
  PRIVATE,
  PUBLIC,
  COURSE,
}
