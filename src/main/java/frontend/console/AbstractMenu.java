package frontend.console;

import controllers.MainController;
import domain.Gebruiker;
import util.ScannerWrapper;

public abstract class AbstractMenu {

    abstract public void load();

    abstract public void load(String ... messages);

    abstract public int laatGebruikerKiezen(String ... keuzes);

    abstract public void toonMenuHeader(String ... messages);

    abstract public boolean bevestigOpdracht(String bevestigvraag);

    abstract public String vraagGebruikerInputString(String vraag);

    abstract public Integer vraagGebruikerInputInteger(String vraag);


    //TODO: mock this, move to a menu util class
    protected int optieKiezer(){
        ScannerWrapper scanner = MainController.getInstance().getScanner();  // Get a Scanner object
        System.out.println("Kies een optie (nrs)");
        int o = Integer.parseInt(scanner.nextLine());  // Read user input from System in
        return o;
    }


}
