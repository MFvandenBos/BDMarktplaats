package controllers;

import dao.GebruikerDao;
import dao.exceptions.GebruikerNotFoundException;
import domain.Gebruiker;
import frontend.console.AbstractMenu;
import frontend.console.RegistreerDialog;
import frontend.swing.Jpassword;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static util.Util.isValidEmail;

public class LoginMenuController extends AbstractController implements ActionListener {


    Gebruiker inlogGebruiker;
    GebruikerDao gebruikerDao;
    AbstractController terug;
    MainController mc;
    HoofdMenuIngelogdController forward;
    Jpassword jpassword;

    private static String OK = "ok"; //TODO: dit kan zo echt niet (zie fronted.swing.Jpassword)
    private static String HELP = "help";

    public LoginMenuController(AbstractController terug){
        MainController mc = MainController.getInstance();
        boolean mctrue = mc.isUseJPassword();
        EntityManager em = mc.getEntityManager();
        super.currentView = new RegistreerDialog();
        this.terug = terug;
        gebruikerDao = new GebruikerDao(em);
        this.forward = new HoofdMenuIngelogdController();
    }

    @Override
    public void load() {
        String email = askEmail();
        try {
            Gebruiker gebruiker = gebruikerDao.selectWithEmail(email);
            load(gebruiker);
        } catch (GebruikerNotFoundException e) {
            String uitvoer = email + "Is niet bekend in ons systeem, geef een juist email adress op, of type q om te beeindigen";
            currentView.toon(uitvoer);
            load();
        }

    }

    @Override
    public void load(Gebruiker gebruiker) {
        inlogGebruiker = gebruiker;
        mc = MainController.getInstance();
        boolean useJP = mc.isUseJPassword();
        if(mc.isUseJPassword()){
            jpassword = Jpassword.createAndShowGUIPassword(gebruiker.getEmailAdress(),this);
        }else{

        }

    }

    private String askEmail(){
        String email;
        email = currentView.vraagGebruikerInputString("Wat is je email adres? ");
        while (!isValidEmail(email)) {
            email = currentView.vraagGebruikerInputString(
                    email + "Is geen valide email adres, geef een juist email adress op, of type q om te beeindigen");
            if (email.equals("q")) {
                terug();
            }
        }
        return email;
    }

    private void terug() {
        terug.load();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (OK.equals(cmd)) { //Process the password.
            char[] input = jpassword.getPasswordField().getPassword();

            boolean correct = inlogGebruiker.verifyPassword(input);
            if(correct) {
                jpassword.showCorrect();
                jpassword.closeFrame();
                        forward.load(inlogGebruiker);
            }else{
                Arrays.fill(input, '0');
                jpassword.setFail();
            }
        } else { //The user has asked for help.
            jpassword.showHelp();
        }
    }
}
