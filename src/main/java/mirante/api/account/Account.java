package mirante.api.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import mirante.api.course.Course;

@Entity
public class Account {
  
  @Id
  private String registration;
  private String name;
  private String email;
  private String password;
  private String token;

  @ManyToOne @JoinColumn(name = "account_course")
  private Course course;

  public Account(
      String registration,
      String name,
      String email,
      String password,
      Course course) {
    this.registration = registration;
    this.name = name;
    this.email = email;
    this.password = SecUtils.encoder.encode(password);
    this.course = course;
  }

  Account() {}

  public Boolean checkPassword(String password) {
    if (SecUtils.encoder.matches(password, this.password)) {
      return true;
    } else {
      return false;
    }
  }

  public void changePassword(String old_password, String new_password) {
    if (SecUtils.encoder.matches(old_password, this.password)) {
      this.password = SecUtils.encoder.encode(new_password);
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
  public Course getCourse() { return course; }
  public void setCourse(Course course) { this.course = course; }
  String getToken() { return token; }
  void setToken(String token) { this.token = token; }
}
