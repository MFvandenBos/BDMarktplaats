package dao.exceptions;

public class GebruikerNotFoundException extends Throwable {
    public GebruikerNotFoundException(){}
    public GebruikerNotFoundException(String problem)
    {
        super(problem);
    }
}
