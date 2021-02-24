package mk.finki.ukim.mk.lab.model.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("User with username " + username + " already exists in our system!");
    }
}
