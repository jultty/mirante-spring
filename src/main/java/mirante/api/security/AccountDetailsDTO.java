package mirante.api.security;

public class AccountDetailsDTO {
  public String registration;
  public String password;

  public AccountDetailsDTO(String registration, String password) {
    this.registration = registration;
    this.password = password;
  }
}
