package controllers;

import controllers.exceptions.WrongInstanceException;
import dao.ArtikelDao;
import domain.Gebruiker;
import factories.ArtikelFactory;
import frontend.console.RegistreerDialog;

import javax.persistence.EntityManager;

public class AanbiedenArtikelController extends AbstractController {

    ArtikelFactory artikelFactory;
    AbstractController terug;
    ArtikelDao artikelDao;

    public AanbiedenArtikelController(AbstractController terug){
        super.currentView = new RegistreerDialog();
        artikelFactory = new ArtikelFactory();
        EntityManager em = MainController.getInstance().getEntityManager();
        artikelDao = new ArtikelDao(em);
        this.terug = terug;
    }

    @Override
    public void load() {
        currentView.toon("deze functie is nog niet geimplementeerd");
        terug();
    }

    @Override
    public void load(Gebruiker gebruiker) {
        currentView.toon("deze functie is nog niet geimplementeerd");
        try {
            terug(gebruiker);
        } catch (WrongInstanceException e) {
            e.printStackTrace();
        }
    }

    private void terug() { terug.load();
    }

    private void terug(Gebruiker gebruiker) throws WrongInstanceException { terug.load(gebruiker);
    }
}
