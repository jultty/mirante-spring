package mirante.api.account;

import java.util.Optional;
import java.util.UUID;
import jakarta.inject.Inject;

import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountService {

  @Inject
  AccountRepository accountRepository;

  public Optional<String> login(String registration, String password) {

    Account account = accountRepository.getReferenceById(registration);

    if (account.checkPassword(password)) {
      String token = UUID.randomUUID().toString();
      account.setToken(token);
      accountRepository.save(account);
      return Optional.of(token);
    }
    return Optional.of("Authentication failed");
  }

  public Optional<Account> findByToken(String token) {

    Optional<Account> accountOpt =
      Optional.of(accountRepository.findByToken(token));

    if (accountOpt.isPresent()) {
      return Optional.of(accountOpt.get());
    }
    return Optional.empty();

  }
}
