package frontend.console;

import controllers.MainController;
import domain.Gebruiker;
import frontend.console.exceptions.NotANumberException;
import util.ScannerWrapper;

import java.util.Scanner;

public abstract class AbstractDialog extends AbstractMenu {

    public boolean vraagGebruikerInputBoolean(String vraag) {
       String respons = vraagStellerString(vraag+"(J/N)");
       boolean slechtantwoord = true;
       while(slechtantwoord){
           respons = respons.toLowerCase();
           if(respons.equals("j")||respons.equals("ja")){
               return true;
           }else if(respons.equals("n")||respons.equals("nee")){
               return false;
           }
       }
       throw new RuntimeException("vraagGebruikerInputBoolean heeft gefaald");
    }

    abstract public void toonRegelement(String... regels);
}

