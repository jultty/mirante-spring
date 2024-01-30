package mirante.api.account;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
class AccountController {
  private final AccountRepository repository;

  @Autowired
  private AccountService accountService;

  AccountController(AccountRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/account")
  List<Account> all() {

    return repository.findAll();
  }

  @PostMapping("/account")
  @ResponseStatus(HttpStatus.CREATED)
  Account newAccount(@RequestBody AccountRequest request) {

    Account newAccount = new Account(
      request.registration,
      request.name,
      request.email,
      request.password,
      request.course
    );

    return repository.save(newAccount);
  }

  @GetMapping("/account/{id}")
  Account one(@PathVariable String id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Account with id ${id} not found")
          );
  }

  @DeleteMapping("/account/{id}")
  void deleteAccount(@PathVariable String id) {
    repository.deleteById(id);
  }

  @GetMapping("/token")
  String getToken(@RequestBody AccountRequest request) {

      Optional<String> token =
        accountService.login(request.registration, request.password);

      if (token.isPresent()) {
        return "{\"token\":\"" + token.orElseThrow() + "\"}";
      } else
        return "Failed to generate token";
      }
}
