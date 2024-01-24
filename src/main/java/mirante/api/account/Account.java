package mirante.api.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Entity
public class Account {
  
  @Id
  private String registration;
  private String name;
  private String email;
  private String password;

  @Transient
  private Argon2PasswordEncoder encoder = 
    Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

  public Account(String registration, String name, String email, String password) {
    this.registration = registration;
    this.name = name;
    this.email = email;
    this.password = encoder.encode(password);
  }

  Account() {}

  public Boolean checkPassword(String password) {
    if (encoder.matches(password, this.password)) {
      return true;
    } else {
      return false;
    }
  }

  public void changePassword(String old_password, String new_password) {
    if (encoder.matches(old_password, this.password)) {
      this.password = encoder.encode(new_password);
    }
  }

  public void resetPassword(String old_password, String new_password) {
     throw new UnsupportedOperationException();
  }

  public String getRegistration() { return registration; }
  public void setRegistration(String registration) { this.registration = registration; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
}

