package mk.finki.ukim.mk.lab.model.exceptions;

public class BalloonEmptyNameException extends RuntimeException{
    public BalloonEmptyNameException() {
        super("Balloon's name is empty");
    }
}
