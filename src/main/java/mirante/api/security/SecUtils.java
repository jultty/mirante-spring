package mirante.api.security;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class SecUtils {

  public static Argon2PasswordEncoder encoder =
    Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

}
