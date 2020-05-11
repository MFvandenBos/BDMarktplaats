package controllers;

import domain.Gebruiker;
import factories.GebruikerType;
import frontend.console.*;
import frontend.console.exceptions.NotANumberException;

public class HoofdMenuNietIngelogdController extends AbstractController{

    //Warning: order of strings is hardcoded in 'functionSwitch'
    String[] functionList = new String[]{"login","registreer nieuwe gebruiker", "toggle Jpassword","exit"};
    MainController mc;

    public HoofdMenuNietIngelogdController(){
        super.currentView = new StandaardMenu();
        mc = MainController.getInstance();
    }

    @Override
    public void load(Gebruiker gebruiker) {
        LoginMenuController loginMenu = new LoginMenuController(this);
        loginMenu.load(gebruiker);
    }

    @Override
    public void load(){
        currentView.load("BDMarktplaats", "Hoofdmenu");
        int fun = 0;
        try {
            fun = currentView.laatGebruikerKiezen(functionList);
        }catch (NotANumberException delta){
            String header = "Voer alleen nummers in";
            loadWithHeader(header);
        }
        functionSwitch(fun);
    }

    private void loadWithHeader(String header){
        currentView.toonMenuHeader(header);
        int optie =0;
        try {
            optie = currentView.laatGebruikerKiezen(functionList);
        }catch (NotANumberException delta){
            header = "Voer alleen nummers in";
            loadWithHeader(header);
        } catch(RuntimeException ex){
            System.out.println("Invoerfout, --reload hoofdmenu");
            load();
        }
        functionSwitch(optie);
    }

    private void functionSwitch(int fun){
        switch (fun){
            case 1:
                LoginMenuController loginMenu = new LoginMenuController(this);
                loginMenu.load(); break;
            case 2:
                RegistreerController registreerController = new RegistreerController(GebruikerType.BEZOEKER, this);
                registreerController.load(); break;
            case 3:
                if(mc.isUseJPassword()){
                    mc.setUseJPassword(false);
                }else{
                    mc.setUseJPassword(true);
                }
                load(); break;
            case 4:
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
