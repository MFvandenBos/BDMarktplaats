package factories;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractFactory {
    String identifier;


    abstract public ArrayList<String> getQuestions(String identifier);
    abstract public HashMap validate(HashMap x, String ... keys);
    abstract public String getIdentifier();
    abstract public void setIdentifier(String identifier);
}
