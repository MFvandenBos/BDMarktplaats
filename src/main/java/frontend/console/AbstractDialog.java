package frontend.console;

import domain.Gebruiker;

import java.util.Scanner;

public abstract class AbstractDialog extends AbstractMenu {

    //TODO: mock this, move to a menu util class
    protected int vraagStellerInt(String vraag){
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println(vraag);
        int o = Integer.parseInt(scanner.nextLine());  // Read user input
        scanner.close();
        return o;
    }

    //TODO: mock this, move to a menu util class
    protected String vraagStellerString(String vraag){
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println(vraag);
        String o = scanner.nextLine();  // Read user input
        scanner.close();
        return o;
    }

}
