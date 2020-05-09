package controllers;

import domain.Address;
import domain.Bezoeker;
import domain.Gebruiker;
import factories.AddressFactory;
import frontend.console.AbstractDialog;
import frontend.console.RegistreerDialog;
import frontend.console.exceptions.NotANumberException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.FileNotFoundException;

public class AdresController extends AbstractController {

    AbstractController terug;
    AbstractDialog specialView;
    AddressFactory factory;


    public AdresController(AbstractController terug){
        this.terug = terug;
        specialView = new RegistreerDialog();
        factory = new AddressFactory();
    }
    @Override
    public void load() {
        throw new NotImplementedException();
    }

    @Override
    public void load(Gebruiker gebruiker)  {
        specialView.toon("Geef alstublieft uw adres op.");
        String straatnaam = specialView.vraagGebruikerInputString("geef uw straatnaam op: ");
        int huisnummer = vraagHuisnummer();
        String postcode = specialView.vraagGebruikerInputString("geef uw postcode op: ");
        String woonplaats = specialView.vraagGebruikerInputString("geef uw woonplaats op: ");

        if(checkLand()){
            if(checkAdres(straatnaam, huisnummer, postcode, woonplaats)){
                Address adres = factory.create(straatnaam,huisnummer,woonplaats,postcode, "Nederland");
                gebruiker.setAdres(adres);
            }
        }else{
            String land = specialView.vraagGebruikerInputString("geef de naam van uw land op: ");
            if(checkAdres(straatnaam, huisnummer, postcode, woonplaats,land)){
                Address adres = factory.create(straatnaam,huisnummer,woonplaats,postcode, land);
                gebruiker.setAdres(adres); //#pass by reference
            }
        }
    }

    private int vraagHuisnummer(){
        int huisnummer;
        boolean blijfvragen = true;
        while(blijfvragen) {
            try {
                huisnummer = specialView.vraagGebruikerInputInteger("geef uw huisnummer op: (alleen getal)");
                blijfvragen = false;
                return huisnummer;
            } catch (NotANumberException e) {
                specialView.toon("alleen nummers toegestaan, probeer opnieuw");
            }
        }
        return 0;
    }

    private boolean checkAdres(String straat, int nummer, String post, String plaats){
        specialView.toon("Uw adres is ", straat +" "+ nummer, post, plaats );
        return specialView.vraagGebruikerInputBoolean("klopt het adres? ");
    }

    private boolean checkAdres(String straat, int nummer, String post, String plaats, String land){
        specialView.toon("Uw adres is ", straat + nummer, post, plaats, land);
        return specialView.vraagGebruikerInputBoolean("klopt het adres? ");
    }

    private boolean checkLand(){
        return specialView.vraagGebruikerInputBoolean("Woont u in Nederland?");
    }


}
