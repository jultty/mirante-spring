package mirante.api.account;

import mirante.api.account.Account;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountTest {

  @Test
	void encodedPasswordCanBeDecoded() {
    String password = "xyz0000";
    Account account = new Account("JC000000", "Jane Doe", "jane@doe.com", password);
    assertTrue(account.checkPassword(password));
	}

}
