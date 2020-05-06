package frontend.console;

import java.sql.SQLOutput;

public class main {

    public static void main(String[] args) {
        MenuBuilder menuBuilder = new MenuBuilder();
        System.out.print(menuBuilder.createMenuHeader("BD Marktplaats", "Hoofdmenu"));
    }
}
