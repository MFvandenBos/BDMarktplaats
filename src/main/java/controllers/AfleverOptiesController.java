package controllers;

import controllers.exceptions.WrongInstanceException;
import domain.Bezoeker;
import domain.Gebruiker;
import domain.exceptions.NoKnownAddressException;
import frontend.console.AbstractDialog;
import frontend.console.RegistreerDialog;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class AfleverOptiesController extends AbstractController {

    AbstractDialog specialView;

    @Override
    public void load() {
            throw new NotImplementedException();
    }

    @Override
    public void load(Gebruiker gebruiker) throws WrongInstanceException {
        if(gebruiker instanceof Bezoeker){
            load((Bezoeker)gebruiker);
        }else{
            throw new WrongInstanceException("Aflever opties alleen beschikbaar voor bezoekers; "+ gebruiker.toString() + "is geen bezoeker!");
        }
    }

    public void load(Bezoeker bezoeker){
        super.currentView = new RegistreerDialog();
        specialView = new RegistreerDialog();
        registreerThuisAfhalen(bezoeker);
        registreerVersturen(bezoeker);
        registreerVersturenRembours(bezoeker);
        registreerMagazijnAfhalen(bezoeker);
    }

    private void registreerThuisAfhalen(Bezoeker bezoeker) {
        boolean thuis = specialView.vraagGebruikerInputBoolean("Wilt u dat kopers artikelen bij u thuis kunnen afhalen?");
        if(thuis){
            AdresController adresController = new AdresController(this);
            adresController.load(bezoeker);
            try {
                bezoeker.setThuisAfhalen(true);
            } catch (NoKnownAddressException e) { e.printStackTrace(); }//should never be executed.
        }else{
            try {
                bezoeker.setThuisAfhalen(false);
            } catch (NoKnownAddressException e) { e.printStackTrace(); }//should never be executed.
        }
    }

    private void registreerVersturen(Bezoeker bezoeker) {
            boolean versturen = specialView.vraagGebruikerInputBoolean("Wilt verkocht artikelen kunnen opsturen naar de koper?");
            bezoeker.setVersturen(versturen);
    }

    private void registreerVersturenRembours(Bezoeker bezoeker) {
        specialView.toon("Bij versturen onder rembours wordt er een extra bedrag van 7,50 per product aan kosten in rekening gebracht",
                "samen met het totaalbedrag te voldoen aan de postbode");
        boolean versturenR = specialView.vraagGebruikerInputBoolean("Wilt verkocht artikelen kunnen opsturen naar de koper met rembours?");
        bezoeker.setVersturenRembours(versturenR);
    }

    private void registreerMagazijnAfhalen(Bezoeker bezoeker) {
        if(bezoeker.isThuisAfhalen() || bezoeker.isVersturen() || bezoeker.isVersturenRembours()){
            specialView.toon("Wilt u dat kopers van uw artikelen deze in het magazijn kunnen afhalen?");
            boolean magazijn = specialView.vraagGebruikerInputBoolean("Dat betekent dat u door u verkochte artikelen naar het magazijn moet brengen");
            bezoeker.setMagazijnAfhalen(magazijn);
        }else{
            specialView.toon("Omdat er geen andere bezorg opties zijn geselecteerd is magzijn ophalen standaard aan",
                    "dit is alleen van belang als u artikelen gaat aanbieden");
            bezoeker.setMagazijnAfhalen(true);
        }

    }



}
