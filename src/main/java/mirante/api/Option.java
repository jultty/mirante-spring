package mirante.api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Option {
  @Id
  private String id;
  private Integer place;
  private String content;

  public Option(Integer place, String content) {
    this.place = place;
    this.content = content;
  }
}
