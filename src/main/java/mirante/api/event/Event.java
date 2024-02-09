package mirante.api.event;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @JsonProperty("time") private Timestamp time;
  @JsonProperty("description") private String description;
  @JsonProperty("content") private String content;

  public Event() {}

  public Event(Timestamp time, String description, String content) {
    this.time = time;
    this.description = description;
    this.content = content;
  }
}

