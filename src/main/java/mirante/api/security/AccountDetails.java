package mirante.api.security;

import mirante.api.account.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AccountDetails implements UserDetails {

  private Account account;

  public AccountDetails(Account account) {
    this.account = account;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getPassword() {
    return account.getPassword();
  }

  @Override
  public String getUsername() {
    return account.getRegistration();
  }

  @Override
  public boolean isAccountNonExpired() {
    return !account.getExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return !account.getLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !account.getExpired();
  }

  @Override
  public boolean isEnabled() {
    return !account.getLocked();
  }
}
