package frontend.console;

public class IngelogdMenu extends AbstractMenu {
    MenuBuilder menuBuilder;

    public IngelogdMenu() {
        menuBuilder = new MenuBuilder();
    }

    @Override
    public void load() {
    }

    @Override
    public void load(String... messages) {
        toonMenuHeader(messages);
    }

    @Override
    public int laatGebruikerKiezen(String... keuzes) {
        //returns user choice as int of location keuze
        System.out.println(menuBuilder.createMenu(keuzes));
        return optieKiezer();
    }

    @Override
    protected void toonMenuHeader(String... messages) {
        System.out.println( menuBuilder.createMenuHeader(messages));
    }

    @Override
    public boolean bevestigOpdracht(String bevestigvraag) {
        return false;
    }

    protected int keuzeMenuMetHeader(String[] headerMessages, String[] keuzes){
        toonMenuHeader(headerMessages);
        return laatGebruikerKiezen(keuzes);
    }

    protected void toonGebruiker(String email, String saldo, String )



}
