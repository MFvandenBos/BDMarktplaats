package factories;

import controllers.MainController;
import dao.ArtikelDao;
import dao.CategorieDao;
import dao.GebruikerDao;
import domain.Artikel;
import domain.Categorie;
import domain.Gebruiker;
import domain.Soort;
import domain.exceptions.InvalidEmailException;
import domain.exceptions.InvalidPasswordException;

import javax.persistence.EntityManager;

public class RandomFactory {

    public static void filldatabase() throws InvalidEmailException, InvalidPasswordException {
        MainController mc = MainController.getInstance();
        EntityManager em = mc.getEntityManager();
        Categorie decoratie = new Categorie("decoratie", Soort.PRODUCT);
        Categorie kleding = new Categorie("kleding", Soort.PRODUCT);
        Categorie computer = new Categorie("computer", Soort.PRODUCT);
        Categorie tuinonderhoud = new Categorie("grasmaaien", Soort.DIENST);
        Categorie tuinplanten = new Categorie("tuinplanten", Soort.PRODUCT);

        GebruikerFactory gebruikerFactory = new GebruikerFactory();

        Gebruiker merchandise = gebruikerFactory.create(GebruikerType.BEZOEKER,"merchandise@Belastingdienst.nl", "merchWoord1");
        Gebruiker randomGebruikerTuin = gebruikerFactory.create(GebruikerType.BEZOEKER, "groeneVingers@email.nl", "groenWoord1");
        Gebruiker randomGebruikerDeco = gebruikerFactory.create(GebruikerType.BEZOEKER, "goudenHanden@email.nl", "goudWoord1");
        merchandise.setRegelementAkkoord(true);
        randomGebruikerDeco.setRegelementAkkoord(true);
        randomGebruikerTuin.setRegelementAkkoord(true);

        Artikel graszaad = new Artikel("overgebleven graszaad 250 g", tuinplanten, "1.00", randomGebruikerTuin);
        Artikel grasmaaien = new Artikel("grasmaaien door mijn zoon, prijs per m2", tuinonderhoud, "0.20", randomGebruikerTuin);
        Artikel lkinmtshirt = new Artikel("Leuker kan ik het niet maken t-shirt", kleding, "7.50", merchandise);
        Artikel schilderen = new Artikel("Eigengemaakt schilderij", decoratie, "50.00", randomGebruikerDeco);

        ArtikelDao artikelDao = new ArtikelDao(em);
        GebruikerDao gebruikerDao = new GebruikerDao(em);
        CategorieDao categorieDao = new CategorieDao(em);

        categorieDao.insert(decoratie,kleding,tuinonderhoud,tuinplanten,computer);
        gebruikerDao.insert(randomGebruikerDeco,randomGebruikerTuin,merchandise);


    }
}
