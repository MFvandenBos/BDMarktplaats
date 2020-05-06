package frontend.console;

import domain.Gebruiker;
import factories.GebruikerType;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class HoofdMenuNietIngelogd extends AbstractMenu{

    //Warning: order of strings is hardcoded in 'functionSwitch'
    String[] functionList = new String[]{"login","registreer nieuwe gebruiker", "exit"};

    @Override
    public void load(Gebruiker gebruiker) {

    }

    public void load(){
        MenuBuilder menuBuilder = new MenuBuilder();
        loadWithHeader(menuBuilder.createMenuHeader("BD Marktplaats", "Hoofdmenu"));
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
                RegistreerDialog registreerDialog = new RegistreerDialog(GebruikerType.BEZOEKER, this);
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
        int input = JOptionPane.showConfirmDialog(null, "Wilt u het programma beëindigen? ?");
        // 0=yes, 1=no, 2=cancel
        switch (input){
            case 0:
                System.exit(0); break;
            default:
                break;
        }

    }
}
