package mk.finki.ukim.mk.lab.model.exceptions;

public class BalloonEmptyDescriptionException extends RuntimeException {
    public BalloonEmptyDescriptionException() {
        super("Balloon's description is empty");
    }
}
