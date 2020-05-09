package frontend.console;

import controllers.MainController;
import domain.Gebruiker;
import frontend.console.exceptions.NotANumberException;
import util.ScannerWrapper;

import javax.swing.*;

public abstract class AbstractMenu {

    abstract public void load();

    abstract public void load(String ... messages);

    abstract public int laatGebruikerKiezen(String ... keuzes) throws NotANumberException;

    abstract public void toonMenuHeader(String ... messages);



    public String vraagGebruikerInputString(String vraag) {
        return vraagStellerString(vraag);
    }

    abstract public void toonGebruiker(String email, String saldo);

    public Integer vraagGebruikerInputInteger(String vraag) throws NotANumberException {
        return vraagStellerInt(vraag);
    }

    public void toon(String ... messages){
        for(String message : messages){
            System.out.println(message);
        }
    }

    protected int optieKiezer() throws NumberFormatException, NotANumberException {
        ScannerWrapper scanner = MainController.getInstance().getScanner();  // Get a Scanner object
        System.out.println("Kies een optie (nrs)");
        try {
            int o = Integer.parseInt(scanner.nextLine());  // Read user input from System in
            return o;
        }catch (NumberFormatException delta){
            throw new NotANumberException(delta.getMessage());
        }
    }

    protected Integer vraagStellerInt(String vraag) throws NotANumberException {
        ScannerWrapper scanner = MainController.getInstance().getScanner();  // Get a Scanner object
        System.out.println(vraag);
        try {
            return Integer.parseInt(scanner.nextLine());  // Read user input
        }catch (NumberFormatException delta){
            throw new NotANumberException(delta.getMessage());
        }
    }

    protected String vraagStellerString(String vraag){
        ScannerWrapper scanner = MainController.getInstance().getScanner();  // Get a Scanner object
        System.out.println(vraag);
        String o = scanner.nextLine();  // Read user input
        return o;
    }

    public boolean bevestigOpdracht(String bevestigvraag) {
        int input = JOptionPane.showConfirmDialog(null, bevestigvraag);
        // 0=yes, 1=no, 2=cancel
        switch (input){
            case 0:
                return true;
            case 1:
            default:
                return false;
        }
    }



}
