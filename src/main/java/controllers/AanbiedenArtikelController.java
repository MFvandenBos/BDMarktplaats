package controllers;

import dao.ArtikelDao;
import domain.Gebruiker;
import factories.ArtikelFactory;

import javax.persistence.EntityManager;

public class AanbiedenArtikelController extends AbstractController {

    ArtikelFactory artikelFactory;
    AbstractController terug;
    ArtikelDao artikelDao;

    public AanbiedenArtikelController(AbstractController terug){
        artikelFactory = new ArtikelFactory();
        EntityManager em = MainController.getInstance().getEntityManager();
        artikelDao = new ArtikelDao(em);
        this.terug = terug;
    }

    @Override
    public void load() {

    }

    @Override
    public void load(Gebruiker gebruiker) {

    }
}
