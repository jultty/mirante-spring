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
  @JsonProperty("timestamp") private Timestamp timestamp;
  @JsonProperty("description") private String description;
  @JsonProperty("content") private String content;

  public Event() {}

  public Event(Timestamp timestamp, String description, String content) {
    this.timestamp = timestamp;
    this.description = description;
    this.content = content;
  }
}

