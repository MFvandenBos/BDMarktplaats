package frontend.console;

import controllers.MainController;
import domain.Gebruiker;
import util.ScannerWrapper;

import java.util.Scanner;

public abstract class AbstractDialog extends AbstractMenu {

    //TODO: mock this, move to a menu util class
    protected int vraagStellerInt(String vraag){
        ScannerWrapper scanner = MainController.getInstance().getScanner();  // Get a Scanner object
        System.out.println(vraag);
        int o = Integer.parseInt(scanner.nextLine());  // Read user input
        return o;
    }

    //TODO: mock this, move to a menu util class
    protected String vraagStellerString(String vraag){
        ScannerWrapper scanner = MainController.getInstance().getScanner();  // Get a Scanner object
        System.out.println(vraag);
        String o = scanner.nextLine();  // Read user input
        return o;
    }

    protected void toon(String ... messages){

    }



}
