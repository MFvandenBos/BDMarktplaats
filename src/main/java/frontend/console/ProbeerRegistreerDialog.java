package frontend.console;

import factories.GebruikerType;

public class ProbeerRegistreerDialog {

    public static void main(String[] args) {
        HoofdMenuNietIngelogd hMenu = new HoofdMenuNietIngelogd();

        RegistreerDialog rDialog = new RegistreerDialog(GebruikerType.BEZOEKER, hMenu);

        rDialog.load();

        rDialog.load();
    }
}
