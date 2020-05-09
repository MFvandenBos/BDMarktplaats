package frontend.console;

import frontend.console.exceptions.NotANumberException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;

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
        toonMenuHeader(messages);
    }

    @Override
    public int laatGebruikerKiezen(String... keuzes) throws NotANumberException {
        //returns user choice as int of location keuze
        System.out.println(menuBuilder.createMenu(keuzes));
        return optieKiezer();
    }

    @Override
    public void toonMenuHeader(String... messages) {
        System.out.println( menuBuilder.createMenuHeader(messages));
    }

    @Override
    public String vraagGebruikerInputString(String vraag) {
        return vraagStellerString(vraag);
    }

    @Override
    public void toonGebruiker(String email, String saldo) {
        throw new NotImplementedException();
    }

    @Override
    public Integer vraagGebruikerInputInteger(String vraag) throws NotANumberException {
        return vraagStellerInt(vraag);
    }

    @Override
    public void toon(String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }

    protected int keuzeMenuMetHeader(String[] headerMessages, String[] keuzes) throws NotANumberException {
        toonMenuHeader(headerMessages);
        return laatGebruikerKiezen(keuzes);
    }



}
