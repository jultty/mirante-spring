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

  @Autowired
  private AccountService accountService;

  private final AccountRepository repository;

  AccountController(AccountRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/account")
  List<Account> all() {
    return repository.findAll();
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
