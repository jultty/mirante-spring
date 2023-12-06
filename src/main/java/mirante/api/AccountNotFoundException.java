package mirante.api;

public class AccountNotFoundException extends RuntimeException {
    AccountNotFoundException(String id) {
        super("Account with ID " + id + " not found");
    }
}
