package mirante.api.account;

import mirante.api.course.Course;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AccountTest {

  Course course = new Course("LBDI4");

  @Test
	void encodedPasswordCanBeDecoded() {
    String password = "xyz0000";
    Account account =
      new Account("JC000000", "Jane Doe", "jane@doe.com", password, course);
    assertTrue(account.checkPassword(password));
	}

  @Test
	void wrongPasswordCheckReturnsFalse() {
    Account account =
      new Account("JC000000", "Jane Doe", "jane@doe.com", "xyz0000", course);
    assertFalse(account.checkPassword("xyz0001"));
	}
}
