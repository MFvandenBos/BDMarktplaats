package frontend.console;

import controllers.HoofdMenuNietIngelogdController;
import factories.GebruikerType;

public class ProbeerRegistreerDialog {


    //TODO: Delete this file
    public static void main(String[] args) {
        HoofdMenuNietIngelogdController hMenu = new HoofdMenuNietIngelogdController();

        RegistreerDialog rDialog = new RegistreerDialog(GebruikerType.BEZOEKER, hMenu);

        rDialog.load();

        rDialog.load();
    }
}
