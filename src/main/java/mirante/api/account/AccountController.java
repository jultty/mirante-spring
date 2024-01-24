package mirante.api.account;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
class AccountController {
  private final AccountRepository repository;

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
      request.password
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
}
