package mirante.api.security;

import mirante.api.account.Account;
import mirante.api.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountDetailsService implements UserDetailsService {

  @Autowired
  private AccountRepository repository;

  @Override
  public UserDetails loadUserByUsername(String registration) throws UsernameNotFoundException {
    Account account = repository.findByRegistration(registration);
    if (account == null) {
      throw new UsernameNotFoundException(registration);
    }
    return new AccountDetails(account);
  }

  public Optional<String> login(String registration, String password) {

    Account account = repository.getReferenceById(registration);

    if (account.checkPassword(password)) {
      String token = UUID.randomUUID().toString();
      account.setToken(token);
      repository.save(account);
      return Optional.of(token);
    }
    return Optional.empty();
  }

  public Optional<Account> findByToken(String token) {

    Optional<Account> accountOpt =
        Optional.of(repository.findByToken(token));

    return Optional.of(accountOpt.orElseThrow());

  }
}
