package mirante.api.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import mirante.api.security.SecUtils;

@Entity
public class Account {

  @Id @Column(nullable = false, unique = true)
  private String registration;
  private String name;
  private String email;
  private String password;
  private String token;
  private Boolean verified;
  private Boolean locked;
  private Boolean expired;

  public Account(
      String registration,
      String name,
      String email,
      String password
  ) {
    this.registration = registration;
    this.name = name;
    this.email = email;
    this.password = SecUtils.encoder.encode(password);
    this.verified = false;
    this.locked = false;
    this.expired = false;
  }

  Account() {}

  public Boolean checkPassword(String password) {
    return SecUtils.encoder.matches(password, this.password);
  }

  public String getPassword() { return password; }
  public void setPassword(String old_password, String new_password) {
    if (SecUtils.encoder.matches(old_password, this.password)) {
      this.password = SecUtils.encoder.encode(new_password);
    }
  }

  public String getRegistration() { return registration; }
  public void setRegistration(String registration) { this.registration = registration; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getToken() { return token; }
  public void setToken(String token) { this.token = token; }
  public Boolean getVerified() { return verified; }
  public void setVerified(Boolean verified) { this.verified = verified; }
  public void setPassword(String password) { this.password = password; }
  public Boolean getLocked() { return locked; }
  public void setLocked(Boolean locked) { this.locked = locked; }
  public Boolean getExpired() { return expired; }
  public void setExpired(Boolean expired) { this.expired = expired; }
}
