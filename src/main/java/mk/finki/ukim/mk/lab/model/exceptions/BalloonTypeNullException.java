package mk.finki.ukim.mk.lab.model.exceptions;

public class BalloonTypeNullException extends RuntimeException{
    public BalloonTypeNullException() {
        super("Balloon has thrown a null exception for it's type");
    }
}
