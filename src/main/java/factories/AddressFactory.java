package factories;

import domain.Address;

import java.util.ArrayList;
import java.util.HashMap;

public class AddressFactory extends AbstractFactory {


    @Override
    public ArrayList<String> getQuestions(String identifier) {
        return null;
    }

    @Override
    public HashMap validate(HashMap x, String... keys) {
        return null;
    }

    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public void setIdentifier(String identifier) {
    }

    public Address create(String straatnaam, int huisnummer, String woonplaats, String postcode,  String land){
        Address uit = new Address(straatnaam, huisnummer, postcode, woonplaats);
        uit.setCountry(land);
        return uit;
    }
}
