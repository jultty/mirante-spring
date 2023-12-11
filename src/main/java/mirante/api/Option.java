package mirante.api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Option {
  @Id @JsonProperty("id") private String id;
  @JsonProperty("place") private Integer place;
  @JsonProperty("content") private String content;

  public Option() {}

  public Option(String id, Integer place, String content) {
    this.id = id;
    this.place = place;
    this.content = content;
  }
}
