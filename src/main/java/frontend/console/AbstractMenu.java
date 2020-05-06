package frontend.console;

import domain.Gebruiker;

import java.util.Scanner;

public abstract class AbstractMenu {

    abstract public void load();

    abstract public void load(Gebruiker gebruiker);


    //TODO: mock this, move to a menu util class
    protected int optieKiezer(){
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Kies een optie (nrs)");
        int o = Integer.parseInt(scanner.nextLine());  // Read user input
        return o;
    }


}
