package mk.finki.ukim.mk.lab.model.exceptions;

public class BalloonNullDescriptionException extends RuntimeException{
    public BalloonNullDescriptionException() {
        super("Balloon has thrown a null exception for it's description");
    }
}
