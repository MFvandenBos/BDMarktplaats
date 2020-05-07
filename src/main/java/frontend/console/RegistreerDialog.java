package frontend.console;

import controllers.RegistreerController;
import dao.GebruikerDao;
import domain.Gebruiker;
import factories.GebruikerFactory;
import factories.GebruikerType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RegistreerDialog extends AbstractDialog {
    MenuBuilder menuBuilder;
    GebruikerFactory gebruikerFactory;
    GebruikerType doel;
    AbstractMenu terug;
    GebruikerDao gebruikerDao;

    public RegistreerDialog(AbstractMenu terug) {

        menuBuilder = new MenuBuilder();//TODO: its not really a builder
        this.terug = terug;
    }

    @Override
    public void load()  {

    }

    @Override
    public void load(String... messages) {

    }

    @Override
    public int laatGebruikerKiezen(String... keuzes) {
        return 0;
    }


    @Override
    public void load(Gebruiker gebruiker) {
        throw new NotImplementedException();
    }

    @Override
    protected void toonMenuHeader(String... messages) {

    }

    @Override
    public boolean bevestigOpdracht(String bevestigvraag) {
        return false;
    }

    private void terug() {
        terug.load();
    }




}

