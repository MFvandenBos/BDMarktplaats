package controllers;

import controllers.exceptions.WrongInstanceException;
import dao.GebruikerDao;
import domain.Bezoeker;
import domain.Gebruiker;
import domain.exceptions.InvalidEmailException;
import domain.exceptions.InvalidPasswordException;
import factories.GebruikerFactory;
import factories.GebruikerType;
import frontend.console.RegistreerDialog;

import javax.persistence.EntityManager;

import static util.Util.*;

public class RegistreerController extends AbstractController{

    GebruikerFactory gebruikerFactory;
    GebruikerType doel;
    AbstractController terug;
    GebruikerDao gebruikerDao;
    AfleverOptiesController afleverOptiesController;
    RegelsController regelsController;
    LoginMenuController loginMenuController;

    public RegistreerController(GebruikerType gebruikerType, AbstractController terug){
        gebruikerFactory = new GebruikerFactory();
        EntityManager em = MainController.getInstance().getEntityManager();
        gebruikerDao = new GebruikerDao(em);
        doel = gebruikerType;
        this.terug = terug;
        this.afleverOptiesController = new AfleverOptiesController();
        this.regelsController = new RegelsController(this);
        this.loginMenuController = new LoginMenuController(terug);
    }

    @Override
    public void load() {
        super.currentView = new RegistreerDialog();
        Gebruiker gebruiker = registreerEmail();
        currentView.toon("Uw email adres is geregistreerd.");
        //TODO: password generator
        load(gebruiker);
    }

    @Override
    public void load(Gebruiker gebruiker) {
        super.currentView = new RegistreerDialog();
        registreerWachtwoord(gebruiker);

        regelsController.load(gebruiker);
        gebruikerDao.update(gebruiker);

        if(doel == GebruikerType.BEZOEKER){
            try {
                afleverOptiesController.load(gebruiker);
            } catch (WrongInstanceException e) {
            }
        }
        loginMenuController.load(gebruiker);
    }

    private void terug() { terug.load();
    }

    private Gebruiker registreerEmail(){
        String email = askEmail();
        try {
            Gebruiker gebruiker = gebruikerFactory.create(doel, email);
            gebruikerDao.insert(gebruiker);
            return gebruiker;
        }catch (InvalidEmailException ex0){
            System.out.println(" Email: " + email + "is geen geldig adres");
            System.out.println("probeer opnieuw: ");
            load();

        }catch (javax.persistence.RollbackException ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            System.out.println("Er bestaat al een account met email: " + email);
            System.out.println("probeer opnieuw: ");
            load();
        }
        throw new RuntimeException("This should never happen, something is wrong!");
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
            System.out.println("Dit password kan niet worden toegepast op deze gebruiker." +
                    " Probeer opnieuw of neem contact op met systeembeheerder");
            load(gebruiker);
        }
    }

    private String askEmail(){
        String email;
        String email2;
        do {
            email = currentView.vraagGebruikerInputString("Wat is je email adres? ");
            while (!isValidEmail(email)) {
                email = currentView.vraagGebruikerInputString(
                        email + "Is geen valide email adres, geef een juist email adress op, of type q om te beeindigen");
                if (email.equals("q")) {
                    terug();
                }
            }
            email2 = currentView.vraagGebruikerInputString("Geef nogmaals je email adres als bevestiging.");
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
            password1 = currentView.vraagGebruikerInputString("Welke wachtwoord wil je gebruiken? " +
                    "(moet minimaal 9 tekens, waaronder minimaal 1 cijfer, en 1 letter bevatten)? ");
            while (!checkPassword(password1, email)) {
                System.out.println("Dit wachtwoord is niet voldoende, het moet minimaal 9 tekens, waaronder minimaal 1 cijfer, en 1 letter bevatten");
                password1 = currentView.vraagGebruikerInputString("geef een beter password");
            }
            password2 = currentView.vraagGebruikerInputString("Geef nogmaals het wachtwoord als bevestiging.");
            if (!password2.equals(password1)) {
                System.out.println("Wachtwoord komt niet overeen, probeer opnieuw");
            }
        } while (!password2.equals(password1));
        return password1;
    }



}
