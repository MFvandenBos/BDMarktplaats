package domain;

import domain.exceptions.InvalidEmailException;
import domain.exceptions.InvalidPasswordException;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Entity
public class Gebruiker extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    //TODO: Indexeer deze kolom, wordt gebruikt in zoekfuncties, email adres mag wel worden gewijzigd, (PK)id niet.
    @Column(unique = true)
    @Email
    @NotNull
    String emailAdress;

    @NotNull
    boolean regelementAkkoord;

    @NotNull
    boolean actiefAccount;

    @Lob
    @NotNull
    private byte[] password;



    public Gebruiker(){};

    public Gebruiker(String emailAdress, String password) throws InvalidPasswordException
    {
        setEmailAdress(emailAdress);
        setPassword(password);
        regelementAkkoord = false;
        actiefAccount = true;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        //TODO: validate email adress.

        this.emailAdress = emailAdress;
    }

    public long getId() {
        return id;
    }

    public boolean containsNONumber(String checked){
        return !checked.matches(".*\\d.*");
    }

    public void setPassword(String password) throws InvalidPasswordException {
        if(password.length()<9
                || password.equals(emailAdress)
                || containsNONumber(password))
        {
            throw new InvalidPasswordException();
        }
        this.password = encodePassword(password);
    }

    private byte[] encodePassword(String password)  {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        }catch (NoSuchAlgorithmException ex)
        {
            throw new RuntimeException(ex.getCause()+" "+ex.getMessage());
        }
        byte[] encodedhash = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));
        return encodedhash;
    }

    public boolean verifyPassword(String password) {
        byte[] verier;
            verier = encodePassword(password);
            return Arrays.equals(verier, this.password);
    }
}
