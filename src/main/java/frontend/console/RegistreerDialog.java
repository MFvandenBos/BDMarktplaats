package frontend.console;


import frontend.console.exceptions.NotANumberException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RegistreerDialog extends AbstractDialog {
    MenuBuilder menuBuilder;
    AbstractMenu terug;

    public RegistreerDialog() {
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
    public int laatGebruikerKiezen(String... keuzes) throws NotANumberException {
        //returns user choice as int of location keuze
        System.out.println(menuBuilder.createMenu(keuzes));
        return optieKiezer();
    }

    @Override
    public void toonMenuHeader(String... messages) {

    }

    @Override
    public void toonGebruiker(String email, String saldo) {
        throw new NotImplementedException();
    }

    @Override
    public void toonRegelement(String... regels){
        String border = menuBuilder.createBorderedOutput("-","|", regels);
        System.out.println(border);
    }

}

