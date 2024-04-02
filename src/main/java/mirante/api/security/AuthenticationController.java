package mirante.api.security;

import mirante.api.account.Account;
import mirante.api.account.AccountDTO;
import mirante.api.account.AccountRepository;
import mirante.api.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Controller
public class AuthenticationController {

  @Autowired
  private AccountService accountService;

  private final AccountRepository repository;

  AuthenticationController(AccountRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/auth/token")
  @ResponseStatus(HttpStatus.OK)
  String getToken(@RequestBody AccountDTO request) {

    Optional<String> token =
        accountService.login(request.registration, request.password);

    if (token.isPresent()) {
      return "{\"token\":\"" + token.orElseThrow() + "\"}";
    } else
      return "Failed to generate token";
  }

  @GetMapping("/auth")
  @ResponseStatus(HttpStatus.OK)
  void authHealth() {}

  @GetMapping("/auth/token/health")
  @ResponseStatus(HttpStatus.OK)
  void token() {}

  @PostMapping("/auth")
  @ResponseStatus(HttpStatus.CREATED)
  Account newAccount(@RequestBody AccountDTO request) {

    Account newAccount = new Account(
        request.registration,
        request.name,
        request.email,
        request.password,
        request.course
    );

    return repository.save(newAccount);
  }

}
