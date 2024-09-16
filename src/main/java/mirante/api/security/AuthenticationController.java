package mirante.api.security;

import jakarta.persistence.EntityNotFoundException;
import mirante.api.account.Account;
import mirante.api.account.AccountDTO;
import mirante.api.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthenticationController {

  @Autowired
  private AccountDetailsService accountDetailsService;

  private final AccountRepository repository;
  private final AuthenticationManager authenticationManager;


  AuthenticationController(AccountRepository repository, AuthenticationManager authenticationManager) {
    this.repository = repository;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/auth/token")
  @ResponseStatus(HttpStatus.CREATED)
  ResponseEntity<String> getToken(@RequestBody AccountDetailsDTO request) {

    Optional<String> token;

    try {
      token =
          accountDetailsService.login(request.registration, request.password);
    } catch (EntityNotFoundException exception) {
      return new ResponseEntity<>( "{\"error\":\"Failed to retrieve token\"}", HttpStatus.BAD_REQUEST);
    }

    if (token.isPresent()) {
      return new ResponseEntity<>("{\"token\":\"" + token.orElseThrow() + "\"}", HttpStatus.CREATED);
    } else
      return new ResponseEntity<>( "{\"error\":\"No matching account found\"}", HttpStatus.BAD_REQUEST);
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
