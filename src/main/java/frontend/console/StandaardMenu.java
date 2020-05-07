package frontend.console;

import domain.Gebruiker;
import factories.GebruikerType;

import javax.persistence.EntityManager;
import javax.swing.*;

import static util.Util.mysql;

public class StandaardMenu extends AbstractMenu {
    MenuBuilder menuBuilder;

    public StandaardMenu() {
        menuBuilder = new MenuBuilder();
    }

    @Override
    public void load() {
        toonMenuHeader("Welkom");
    }

    @Override
    public void load(String... messages) {
        toonMenuHeader( messages);
    }

    @Override
    public int laatGebruikerKiezen(String... keuzes) {
        //returns user choice as int of location keuze
        System.out.println(menuBuilder.createMenu(keuzes));
        return optieKiezer();
    }

    @Override
    protected void toonMenuHeader(String... messages) {
        System.out.println( menuBuilder.createMenuHeader(messages));
    }

    @Override
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

    protected int keuzeMenuMetHeader(String[] headerMessages, String[] keuzes){
        toonMenuHeader(headerMessages);
        return laatGebruikerKiezen(keuzes);
    }



}
