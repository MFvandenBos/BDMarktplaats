package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Util {

    public static Logger logger(Class<?> c) { return LoggerFactory.getLogger(c); }

    public static EntityManager mysql() { return Persistence.createEntityManagerFactory("MySQL").createEntityManager();}

    public static EntityManager h2() { return Persistence.createEntityManagerFactory("H2").createEntityManager();}

    //TODO: this method is duplicate with 'Gebruiker.class'
    public static boolean containsNONumber(String tobechecked) {
        return !tobechecked.matches(".*\\d.*");
    }

    public static boolean checkPassword(String passwordToBeChecked, String email) {
        if (passwordToBeChecked.length() < 9
                || passwordToBeChecked.equals(email)
                || containsNONumber(passwordToBeChecked)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
