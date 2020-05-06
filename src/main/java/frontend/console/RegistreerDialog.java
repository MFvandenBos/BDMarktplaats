package frontend.console;

import dao.GebruikerDao;
import domain.Gebruiker;
import domain.exceptions.InvalidEmailException;
import domain.exceptions.InvalidPasswordException;
import factories.GebruikerFactory;
import factories.GebruikerType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;

import static util.Util.*;

public class RegistreerDialog extends AbstractDialog {
    MenuBuilder menuBuilder;
    GebruikerFactory gebruikerFactory;
    GebruikerType doel;
    AbstractMenu terug;
    GebruikerDao gebruikerDao;

    public RegistreerDialog(GebruikerType gebruikerType, AbstractMenu terug) {
        //TODO: mock this
        EntityManager em = mysql();

        gebruikerFactory = new GebruikerFactory();
        gebruikerDao = new GebruikerDao(em);
        menuBuilder = new MenuBuilder();
        doel = gebruikerType;
        this.terug = terug;
    }

    @Override
    public void load()  {
        String email = askEmail();
        String password = askPassword(email);
        try {
            Gebruiker gebruiker = gebruikerFactory.create(doel, email, password);
            gebruikerDao.insert(gebruiker);
        }catch (InvalidPasswordException ex){
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            System.out.println("Gebruiker kan niet worden gecreeërd met dit wachtwoord. Probeer opnieuw of neem contact op met systeembeheerder");
            load();
        }catch (javax.persistence.RollbackException ex2){
            System.out.println("Er bestaat al een account met email: "+email);
            System.out.println("probeer opnieuw: ");
            load();
        }

        //TODO: what next?
    }

    private String askEmail(){
        String email;
        String email2;
        do {
            email = vraagStellerString("Wat is je email adres? ");
            while (!isValidEmail(email)) {
                email = vraagStellerString(email + "Is geen valide email adres, geef een juist email adress op, of type q om te beeindigen");
                if (email.equals("q")) {
                    terug();
                }
            }
            email2 = vraagStellerString("Geef nogmaals je email adres als bevestiging.");
            if (!email2.equals(email)) {
                System.out.println("Email adres komt niet overeen, probeer opnieuw");
            }
        } while (!email2.equals(email));
        return email;
    }

    private String askPassword(String email){
        String password1;
        String password2;
        do {
            password1 = vraagStellerString("Welke wachtwoord wil je gebruiken? (moet minimaal 9 tekens, waaronder minimaal 1 cijfer, en 1 letter bevatten)? ");
            while (!checkPassword(password1, email)) {
                System.out.println("Dit password is niet voldoende, het moet minimaal 9 tekens, waaronder minimaal 1 cijfer, en 1 letter bevatten");
                password1 = vraagStellerString("geef een beter password, of type q om te beëindigen");
                if (email.equals("q")) {
                    terug();
                }
            }
            password2 = vraagStellerString("Geef nogmaals het password als bevestiging.");
            if (!password2.equals(password1)) {
                System.out.println("Email adres komt niet overeen, probeer opnieuw");
            }
        } while (!password2.equals(password1));
        return password1;
    }

    @Override
    public void load(Gebruiker gebruiker) {
        throw new NotImplementedException();
    }

    private void terug() {
        terug.load();
    }




}

