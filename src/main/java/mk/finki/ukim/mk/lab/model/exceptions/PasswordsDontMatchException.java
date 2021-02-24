package mk.finki.ukim.mk.lab.model.exceptions;

public class PasswordsDontMatchException extends RuntimeException {
    public PasswordsDontMatchException(String password, String repeatPassword) {
        super("Password " + password + " doesn't match with password " + repeatPassword);
    }
}
