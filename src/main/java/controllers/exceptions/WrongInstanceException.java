package controllers.exceptions;

public class WrongInstanceException extends Exception {
    public WrongInstanceException(){}
    public WrongInstanceException(String problem)
    {
        super(problem);
    }
}
