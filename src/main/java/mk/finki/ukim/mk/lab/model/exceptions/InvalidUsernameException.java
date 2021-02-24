package mk.finki.ukim.mk.lab.model.exceptions;

public class InvalidUsernameException extends RuntimeException{
    public InvalidUsernameException() {
        super("The username is invalid!");
    }
}
