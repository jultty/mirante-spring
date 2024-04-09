package mirante.api.security;

import mirante.api.account.Account;
import mirante.api.account.AccountDTO;
import mirante.api.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthenticationController {

  @Autowired
  private AccountDetailsService accountDetailsService;

  private final AccountRepository repository;

  AuthenticationController(AccountRepository repository) {
    this.repository = repository;
  }

  @PostMapping("/auth/token")
  @ResponseStatus(HttpStatus.CREATED)
  String getToken(@RequestBody AccountDTO request) {

    Optional<String> token =
        accountDetailsService.login(request.registration, request.password);

    if (token.isPresent()) {
      return "{\"token\":\"" + token.orElseThrow() + "\"}";
    } else
      return "{\"error\":\"Failed to generate token\"}";
  }

  @GetMapping("/auth")
  @ResponseStatus(HttpStatus.OK)
  void authHealth() {}

  @GetMapping("/auth/token/health")
  @ResponseStatus(HttpStatus.OK)
  void tokenHealth() {}

  @PostMapping("/auth")
  @ResponseStatus(HttpStatus.CREATED)
  Account newAccount(@RequestBody AccountDTO request) {

    Account newAccount = new Account(
        request.registration,
        request.name,
        request.email,
        request.password
    );

    return repository.save(newAccount);
  }
}
