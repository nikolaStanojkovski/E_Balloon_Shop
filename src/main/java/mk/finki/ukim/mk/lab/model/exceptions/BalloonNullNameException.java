package mk.finki.ukim.mk.lab.model.exceptions;

public class BalloonNullNameException  extends RuntimeException{
    public BalloonNullNameException() {
        super("Balloon has thrown a null exception for it's name");
    }
}
