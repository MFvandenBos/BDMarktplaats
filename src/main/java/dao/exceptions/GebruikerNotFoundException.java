package dao.exceptions;

public class GebruikerNotFoundException extends Exception {
    public GebruikerNotFoundException(){}
    public GebruikerNotFoundException(String problem)
    {
        super(problem);
    }
}
