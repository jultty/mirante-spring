package mirante.api.account;

import mirante.api.course.Course;

public class AccountDTO {

  public String registration;
  public String name;
  public String email;
  public String password;
  public Course course;

  AccountDTO(
      String registration,
      String name,
      String email,
      String password,
      Course course) {
    this.registration = registration;
    this.name = name;
    this.email = email;
    this.password = password;
    this.course = course;
  }
}

