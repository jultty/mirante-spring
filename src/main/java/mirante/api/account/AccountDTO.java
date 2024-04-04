package mirante.api.account;

public class AccountDTO {

  public String registration;
  public String name;
  public String email;
  public String password;

  public AccountDTO(
      String registration,
      String name,
      String email,
      String password
  ) {
    this.registration = registration;
    this.name = name;
    this.email = email;
    this.password = password;
  }
}

