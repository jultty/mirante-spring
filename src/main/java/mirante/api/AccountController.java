package mirante.api;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

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
  Account newAccount(@RequestBody Account newAccount) {
    return repository.save(newAccount);
  }

  @GetMapping("/account/{id}")
  Account one(@PathVariable String id) {
    return repository.findById(id)
      .orElseThrow(() -> new AccountNotFoundException(id));
  }

  @DeleteMapping("/account/{id}")
  void deleteAccount(@PathVariable String id) {
    repository.deleteById(id);
  }
}
