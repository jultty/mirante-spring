package mirante.api.course;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Course {

  @Id
  private String id;

  public Course(String id) { this.id = id; }

  Course() {}

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
}

