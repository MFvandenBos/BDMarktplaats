package controllers;

import domain.Bezoeker;
import domain.Gebruiker;
import factories.GebruikerType;
import frontend.console.IngelogdMenu;
import frontend.console.RegistreerDialog;
import frontend.console.exceptions.NotANumberException;

public class HoofdMenuIngelogdController extends AbstractController {

    //Warning: order of strings is hardcoded in 'functionSwitch'
    String[] functionList = new String[]{"Wijzig Gegevens",
            "Voeg Artikel toe",
            "Beheer eigen artikelen",
            "zoek artikel",
            "winkelmandje",
            "Beheer portomonee",
            "Overzicht gegevens",
            "log uit"};
    Gebruiker ingelogdeGebruiker;

    IngelogdMenu specializedView;

    public HoofdMenuIngelogdController(){
        super.currentView = new IngelogdMenu();
        specializedView = new IngelogdMenu();
    }

    @Override
    public void load() {
        HoofdMenuNietIngelogdController x = new HoofdMenuNietIngelogdController();
        x.load();
    }

    @Override
    public void load(Gebruiker gebruiker) {
        ingelogdeGebruiker = gebruiker;
        currentView.load("BDMarktplaats");
        currentView.toonGebruiker(gebruiker.getEmailAdress(), gebruiker.getSaldo());
        int fun = 0;
        try {
            fun = currentView.laatGebruikerKiezen(functionList);
        }catch (NotANumberException delta){
            String header = "Voer alleen nummers in";
            loadWithHeader(header);
        }
        functionSwitch(fun);
    }

    private void loadWithHeader(String header){
        currentView.toonMenuHeader(header);
        int optie =0;
        try {
            optie = currentView.laatGebruikerKiezen(functionList);
        }catch (NotANumberException delta){
            header = "Voer alleen nummers in";
            loadWithHeader(header);
        } catch(RuntimeException ex){
            System.out.println("Invoerfout, --reload hoofdmenu");
            load(ingelogdeGebruiker);
        }
        functionSwitch(optie);
    }

    private void functionSwitch(int fun){
        switch (fun){
            case 1: //wijzig gegevens
                RegistreerController wijzigMenu = new RegistreerController(GebruikerType.BEZOEKER, this);
                wijzigMenu.load(ingelogdeGebruiker); break;
            case 2: //voeg artikel toe
                AanbiedenArtikelController biedAan = new AanbiedenArtikelController(this);
                biedAan.load(ingelogdeGebruiker); break;
            case 3: //beheer eigen artikelen
                currentView.toon("deze functie is nog niet geimplementeerd");
                load(ingelogdeGebruiker); break;
            case 4: //zoek artikel
                currentView.toon("deze functie is nog niet geimplementeerd");
                load(ingelogdeGebruiker); break;
            case 5: //winkelmandje
                currentView.toon("deze functie is nog niet geimplementeerd");
                load(ingelogdeGebruiker); break;
            case 6: //beheer portomonee
                verhoogSaldo();
                load(ingelogdeGebruiker); break;
            case 7: overzichtGegevens();
                    load(ingelogdeGebruiker); break;
            case 8: logUit(); break;
            default:
                String header = "Optie "+fun+ " bestaat niet in dit menu, kies een van de volgende opties:";
                loadWithHeader(header);
        }
    }

    private void verhoogSaldo(){
        RegistreerDialog extraview = new RegistreerDialog();
        if(extraview.vraagGebruikerInputBoolean("Wilt u uw saldo verhogen?")){
            try {
                int x = currentView.vraagGebruikerInputInteger("Wilt u uw saldo verhogen voeg dan een heel aantal euros in, bijvoorbeeld; 10");
                if(x > 500 ){
                    currentView.toon("max 500, probeer opnieuw");
                    verhoogSaldo();
                }
                if(x < 0 ){
                    currentView.toon("min 0, probeer opnieuw");
                    verhoogSaldo();
                }
                ingelogdeGebruiker.verhoogSaldo(x);
            } catch (NotANumberException e) {
                currentView.toon("voer alleen nummer in, probeer opnieuw");
                verhoogSaldo();
            }
        }
    }

    private void overzichtGegevens() {
        String[] info = new String[]{"Email adres",
                "Saldo",
                "Adres",
                "Akkoord met regelement",
                "Ondersteunt Afhalen Magazijn",
                "Ondersteunt Thuis Afhalen",
                "Ondersteunt Versturen",
                "Ondersteunt Versturen rembours"};
        String[] gegevens = new String[info.length];
        gegevens[0] = ingelogdeGebruiker.getEmailAdress();
        gegevens[1] = ingelogdeGebruiker.getSaldo();
        gegevens[2] = ingelogdeGebruiker.getAdres().toString();
        gegevens[3] = (ingelogdeGebruiker.isRegelementAkkoord()) ? "Ja" : "nee";

        if(ingelogdeGebruiker instanceof Bezoeker){
            Bezoeker bezoeker = (Bezoeker)ingelogdeGebruiker;
            gegevens[4] =(bezoeker.isMagazijnAfhalen()) ? "Ja" : "nee";
            gegevens[5] =(bezoeker.isMagazijnAfhalen()) ? "Ja" : "nee";
            gegevens[6] =(bezoeker.isMagazijnAfhalen()) ? "Ja" : "nee";
            gegevens[7] =(bezoeker.isMagazijnAfhalen()) ? "Ja" : "nee";
        }else {
            String[] gegevens2 = new String[4];
            System.arraycopy(gegevens, 0, gegevens2, 0, 4);
            gegevens = gegevens2;
        }
        specializedView.toonTweeKolommen(info, gegevens);
    }

    private void logUit(){
        if(currentView.bevestigOpdracht( "Wilt u uitloggen? ?")){
            load();
        }else{
            load(ingelogdeGebruiker);
        }

    }
}
