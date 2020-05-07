package controllers;

import dao.GebruikerDao;
import domain.Gebruiker;
import domain.exceptions.InvalidEmailException;
import domain.exceptions.InvalidPasswordException;
import factories.GebruikerFactory;
import factories.GebruikerType;
import frontend.console.AbstractMenu;
import frontend.console.RegistreerDialog;
import frontend.console.StandaardMenu;

import javax.persistence.EntityManager;
import java.security.PublicKey;

import static util.Util.*;

public class RegistreerController extends AbstractController{

    GebruikerFactory gebruikerFactory;
    GebruikerType doel;
    AbstractMenu terug;
    GebruikerDao gebruikerDao;

    public RegistreerController(GebruikerType gebruikerType, AbstractController terug){
        gebruikerFactory = new GebruikerFactory();
        EntityManager em = MainController.getInstance().getEm();
        gebruikerDao = new GebruikerDao(em);
        doel = gebruikerType;
    }

    @Override
    public void load() {
        super.currentView = new RegistreerDialog(doel, new StandaardMenu());
        Gebruiker gebruiker = registreerEmail();
        registreerWachtwoord(gebruiker);
    }

    @Override
    public void load(Gebruiker gebruiker) {

    }

    private Gebruiker registreerEmail(){
        String email = askEmail();
        try {
            Gebruiker gebruiker = gebruikerFactory.create(doel, email);
            gebruikerDao.insert(gebruiker);
            return gebruiker;
        }catch (InvalidEmailException ex0){
            System.out.println("Email: " + email + "is geen geldig adres");
            System.out.println("probeer opnieuw: ");
            load();

        }catch (javax.persistence.RollbackException ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            System.out.println("Er bestaat al een account met email: " + email);
            System.out.println("probeer opnieuw: ");
            load();
        }
        return  null;
    }

    private void registreerWachtwoord(Gebruiker gebruiker){
        String email = gebruiker.getEmailAdress();
        String password = askPassword(email);
        try {
            gebruiker.setPassword(password);
            gebruikerDao.update(gebruiker);
        }catch (InvalidPasswordException ex){
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            System.out.println("Dit password kan niet worden toegepast op deze gebruiker. Probeer opnieuw of neem contact op met systeembeheerder");
            load();
        }
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


}
