
package factories.exceptions;

public class UnableToValidateException extends Exception {
    public UnableToValidateException(){}
    public UnableToValidateException(String problem)
    {
        super(problem);
    }

}
