package controllers;

import dao.GebruikerDao;
import domain.Gebruiker;
import frontend.console.AbstractDialog;
import frontend.console.RegistreerDialog;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import javax.persistence.EntityManager;
import java.io.*;


public class RegelsController extends AbstractController {
    AbstractController terug;
    AbstractDialog specialView;

    public RegelsController(AbstractController terug){
        this.terug = terug;
    }

    @Override
    public void load() {
        throw new NotImplementedException();
    }

    @Override
    public void load(Gebruiker gebruiker) {
        super.currentView = new RegistreerDialog();
        specialView = new RegistreerDialog();
        registreerAkkoordRegels(gebruiker);
    }

    private void registreerAkkoordRegels(Gebruiker gebruiker)  {
        boolean akkoord = gebruiker.isRegelementAkkoord();
        if(akkoord){}
        else{
            try {
                File regelsF = new File(getClass().getClassLoader().getResource("regelement.txt").getFile());
                BufferedReader br = new BufferedReader(new FileReader(regelsF));
                StringBuilder sbR = new StringBuilder();
                String st;
                try {
                    while ((st = br.readLine()) != null) {
                        sbR.append(st);
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
                String[] regels = sbR.toString().split("#regel");
                specialView.toonRegelement(regels);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            boolean ok = specialView.vraagGebruikerInputBoolean("Gaat u akkoord met deze regels?");
            if(ok){
                gebruiker.setRegelementAkkoord(true);
            }else{
                HoofdMenuNietIngelogdController goback = new HoofdMenuNietIngelogdController();
                goback.load();
            }
        }
    }
}

