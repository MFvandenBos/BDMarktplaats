package frontend.console;

import frontend.console.exceptions.NotANumberException;

public class IngelogdMenu extends AbstractMenu {
    MenuBuilder menuBuilder;
    protected static String FORMATTER = "%1$30s   %2$-30s ";

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

    public void update(String email, String saldo){
        toonGebruiker(email, saldo);
    }

    @Override
    public int laatGebruikerKiezen(String... keuzes) throws NotANumberException {
        //returns user choice as int of location keuze
        System.out.println(menuBuilder.createMenu(keuzes));
        return optieKiezer();
    }

    @Override
    public void toonMenuHeader(String... messages) {
        System.out.println( menuBuilder.createMenuHeader(messages));
    }


    @Override
    public void toon(String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }

    protected int keuzeMenuMetHeader(String[] headerMessages, String[] keuzes) throws NotANumberException {
        toonMenuHeader(headerMessages);
        return laatGebruikerKiezen(keuzes);
    }

    public void toonGebruiker(String email, String saldo){
        String output = menuBuilder.createBorderedOutput("x", "x", email, saldo);
        System.out.println(output);
    }

    protected void toonTwoColumns(String[] col1, String[] col2){
        int stop = col1.length
        if(col1.length > col2.length){
            stop = col2.length;
        }
        for(int i = 0; i< stop; i++){
            System.out.println(String.format(FORMATTER+" %4$-2d" ,col1[i],col2[i]));
        }
    }



}
