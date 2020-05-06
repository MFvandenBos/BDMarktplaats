
package domain.exceptions;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(){}
    public InvalidPasswordException(String problem)
    {
        super(problem);
    }

}
