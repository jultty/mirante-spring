package mirante.api.account;

import mirante.api.course.Course;

class AccountRequest {
  
  String registration;
  String name;
  String email;
  String password;
  Course course;

  AccountRequest(
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

