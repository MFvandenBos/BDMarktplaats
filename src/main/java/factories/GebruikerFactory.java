package factories;

import com.google.gson.JsonObject;
import domain.Address;
import domain.Bezoeker;
import domain.Gebruiker;
import domain.exceptions.InvalidEmailException;
import domain.exceptions.InvalidPasswordException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;




public class GebruikerFactory extends AbstractFactory {
    //TODO: regelement maken, ofwel website ofwel lange string
    String regelement = "regels moeten er zijn enz. enz.";
    private String[] bezoekerKeys;

    {
        identifier = "identifier";
        bezoekerKeys = new String[]{
            identifier,
            "email",
            "email_Confirm",
            "password",
            "password_Confirm",
            "regelement",
            "afhalen",
            "magazijn",
            "versturen",
            "versturenRembours"};
    }
    //TODO: dynamic (reflective) KeyMap generator
    private final String bezoekerReg = "bezReg";

    private String bezoekerTitel = "Welkom bij BDMarktplaats, beantwoord de volgende "+(bezoekerKeys.length-1)+" vragen om je te registreren.";
    private String[] bezoekerVragen = new String[]{
            bezoekerKeys[1]+"#:Wat is je email adres? ",
            bezoekerKeys[2]+"#:Geef nogmaals je email adres als bevestiging",
            bezoekerKeys[3]+"#:Welke wachtwoord wil je gebruiken? (moet minimaal 9 tekens, waaronder minimaal 1 cijfer, en 1 letter bevatten)",
            bezoekerKeys[4]+"#:Geef nogmaals je wachtwoord als bevestiging.",
            bezoekerKeys[5]+"#:"+regelement+"Ga je akkoord met het bovenstaande regelement? (J/N)",
            bezoekerKeys[6]+"#:Mogen spullen die je aanbied bij jou thuis worden opgehaald? (J/N)",
            bezoekerKeys[7]+"#:Wil je spullen via het magazijn kunnen aanbieden? (J/N)?",
            bezoekerKeys[8]+"#:Wil je spullen die je aanbied kunnen versturen? (J/N)?",
            bezoekerKeys[9]+"#:Wil je spullen die je aanbied kunnen versturen met rembours?  (J/N)?"};

    private final String medewerkerReg = "medReg";
    private String[] medewerkerKeys = new String[]{identifier, "email", "password"};
    private String medewerkerTitel = "Welkom bij BDMarktplaats, beantwoord de volgende "+(bezoekerKeys.length-1)+" vragen om je te registreren.";
    private String[] medwerkerVragen = new String[5];

    private final String beheerderReg = "behReg";
    private String[] beheerderKeys = new String[]{identifier, "email", "password"};
    private String beheerderTitel = "Welkom bij BDMarktplaats, beantwoord de volgende "+(bezoekerKeys.length-1)+" vragen om je te registreren.";
    private String[] beheerderVragen = new String[5];



    public HashMap validate(HashMap gebruikerMap, String ... keys){
        HashMap out = new HashMap();
        // values
        // valid
        // invalid, reask
        // subquestion

        return out;
    }

    public boolean create(GebruikerType gebruikerType, JsonObject gebruikerMap){

        return true;
    }

    public Gebruiker create(GebruikerType gebruikerType, String email) throws InvalidEmailException {
        switch (gebruikerType){
            case BEZOEKER:
                return createBezoeker(email);
            case MEDEWERKER:
            case BEHEERDER:
            default:
                throw new NotImplementedException();
        }
    }

    public Gebruiker create(GebruikerType gebruikerType, String email, String Password) throws InvalidPasswordException, InvalidEmailException {
        switch (gebruikerType){
            case BEZOEKER:
                return createBezoeker(email, Password);
            case MEDEWERKER:
            case BEHEERDER:
            default:
                throw new NotImplementedException();
        }
    }

    private Gebruiker createBezoeker(String email, String password) throws InvalidPasswordException, InvalidEmailException {
        return new Bezoeker(email, password, false, true, false, false, new Address());
    }

    private Gebruiker createBezoeker(String email) throws InvalidEmailException {
        return new Bezoeker(email, false, true, false, false, new Address());

    }

    private void createBeheerder(){}

    private void createMedewerker(){}

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public HashMap getRegistratie(GebruikerType x){
        switch (x){
            case BEZOEKER:
                return getBezoekerRegistratie();
            case BEHEERDER:
                return getBeheerderRegistratie();
            case MEDEWERKER:
                return getMedewerkerRegistratie();
            default:
                throw new NotImplementedException();
        }
    }

    private HashMap getMedewerkerRegistratie() {
        HashMap bezReg = new HashMap();
        //TODO: investigate JPasswordField
        for (String key:medewerkerKeys) {
            bezReg.put(key, null);
        }
        bezReg.replace(identifier, medewerkerReg);
        return bezReg;
    }

    private HashMap getBeheerderRegistratie() {
        HashMap bezReg = new HashMap();
        //TODO: investigate JPasswordField
        for (String key:beheerderKeys) {
            bezReg.put(key, null);
        }
        bezReg.replace(identifier, beheerderReg);
        return bezReg;
    }

    private HashMap getBezoekerRegistratie(){
        HashMap bezReg = new HashMap();
        //TODO: investigate JPasswordField
        for (String key:bezoekerKeys) {
            bezReg.put(key, null);
        }
        bezReg.replace(identifier, bezoekerReg);
        return bezReg;
    }

    public ArrayList<String> getQuestions(String identifier){
        ArrayList<String>questions = new ArrayList<>();
        String[] vragen;
        switch (identifier){
            case bezoekerReg:
                vragen = bezoekerVragen;
                break;
            case beheerderReg:
                vragen = beheerderVragen;
                break;
            case medewerkerReg:
                vragen = medwerkerVragen;
            default:
                vragen = new String[]{"error!"};
        }
        for (String vraag:vragen) {
            questions.add(vraag);
        }
        return questions;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
