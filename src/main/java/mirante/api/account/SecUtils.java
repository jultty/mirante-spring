package mirante.api.account;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

class SecUtils {
  public static Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
}

