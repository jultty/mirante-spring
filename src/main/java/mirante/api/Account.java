package mirante.api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {
  
  @Id
  private String username;
  private String name;
  private String email;
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    if (password != null) return "set";
    else return "null";
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

