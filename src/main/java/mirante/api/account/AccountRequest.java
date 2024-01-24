package mirante.api.account;

class AccountRequest {
  
  String registration;
  String name;
  String email;
  String password;

  AccountRequest(String registration, String name, String email, String password) {
    this.registration = registration;
    this.name = name;
    this.email = email;
    this.password = password;
  }
}

