package frontend.console;

import controllers.HoofdMenuNietIngelogdController;
import controllers.MainController;

public class main {

    public static void main(String[] args) {

        MainController main = MainController.getInstance();
        System.out.println(main.isUseJPassword());
        MenuBuilder menuBuilder = new MenuBuilder();
        System.out.print(menuBuilder.createMenuHeader("Welkom bij BDMarktplaats"));
        HoofdMenuNietIngelogdController x = new HoofdMenuNietIngelogdController();
        x.load();
    }
}
