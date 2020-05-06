
package domain.exceptions;

public class InvalidEmailException extends Exception {
    public InvalidEmailException(){}
    public InvalidEmailException(String problem)
    {
        super(problem);
    }

}
