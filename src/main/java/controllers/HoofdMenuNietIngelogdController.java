package controllers;

import domain.Gebruiker;
import factories.GebruikerType;
import frontend.console.AbstractMenu;
import frontend.console.MenuBuilder;
import frontend.console.RegistreerDialog;
import frontend.console.StandaardMenu;

import javax.swing.JOptionPane;

public class HoofdMenuNietIngelogdController extends AbstractController{

    //Warning: order of strings is hardcoded in 'functionSwitch'
    String[] functionList = new String[]{"login","registreer nieuwe gebruiker", "exit"};

    public HoofdMenuNietIngelogdController(){
        super.currentView = new StandaardMenu();
    }

    @Override
    public void load(Gebruiker gebruiker) {

    }

    public void load(){
        currentView.load("BDMarktplaats", "Hoofdmenu");

    }

    //TODO: this requires a mock to test
    private void loadWithHeader(String header){
        MenuBuilder menuBuilder = new MenuBuilder();
        System.out.print(header);
        System.out.print(menuBuilder.createMenu(functionList));
        int optie =0;
        try {
            optie = optieKiezer();
        }catch(RuntimeException ex){
            System.out.println("Invoerfout");
        }
        functionSwitch(optie);
    }

    private void functionSwitch(int fun){
        switch (fun){
            case 1:
                LoginMenu loginMenu = new LoginMenu();
                loginMenu.load(); break;
            case 2:
                RegistreerDialog registreerDialog = new RegistreerDialog();
                registreerDialog.load(); break;
            case 3:
                exitProgram();
                load(); break;
            default:
                String header = "Optie "+fun+ " bestaat niet in dit menu, kies een van de volgende opties:";
                loadWithHeader(header);
        }
    }

    private void exitProgram(){
        if(currentView.bevestigOpdracht( "Wilt u het programma beëindigen? ?")){
            System.exit(0);
        }else{
            load();
        }

    }
}
