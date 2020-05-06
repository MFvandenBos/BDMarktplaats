
package domain.exceptions;

public class NoKnownAddressException extends Exception {
    public NoKnownAddressException(){}
    public NoKnownAddressException(String problem)
    {
        super(problem);
    }

}
