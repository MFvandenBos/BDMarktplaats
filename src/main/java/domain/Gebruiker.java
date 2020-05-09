package domain;

import domain.exceptions.InvalidEmailException;
import domain.exceptions.InvalidPasswordException;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.Util.checkPassword;
import static util.Util.isValidEmail;

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

    @Embedded
    Address adres;

    @NotNull
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    Portemonee portemonee;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "verkoper" )
    private List<Artikel> geplaatsteArtikels = new ArrayList<>();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Gebruiker(){};

    public Gebruiker(String emailAdress) throws InvalidEmailException {
        init(emailAdress);
        try {
            setPassword("Aaaaaaaaaa9");
        }catch (InvalidPasswordException e){
            e.printStackTrace();
        }
    }

    public Gebruiker(String emailAdress, String password) throws InvalidPasswordException, InvalidEmailException {
        init(emailAdress);
        setPassword(password);
    }

    private void init(String emailAdress) throws InvalidEmailException {
        setEmailAdress(emailAdress);
        regelementAkkoord = false;
        actiefAccount = true;
        Portemonee por = new Portemonee();
        setPortemonee(por);
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) throws InvalidEmailException {
        if(isValidEmail(emailAdress)){
            this.emailAdress = emailAdress;
        }else {
            throw new InvalidEmailException();
        }

    }

    public long getId() {
        return id;
    }

    public Portemonee getPortemonee() {
        return portemonee;
    }

    public void setPortemonee(Portemonee portemonee) {
        this.portemonee = portemonee;
        portemonee.setGebruiker(this);
    }

    public void setPassword(String password) throws InvalidPasswordException {
        if(!checkPassword(password,this.emailAdress)) {
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

    public boolean verifyPassword(char[] password){
        StringBuilder passwordS = new StringBuilder("");
        for (char x :password){
            passwordS.append(x);
        }
        return verifyPassword(passwordS.toString());
    }

    public boolean verifyPassword(String password) {
        byte[] verier;
            verier = encodePassword(password);
            System.gc();
            return Arrays.equals(verier, this.password);
    }

    public boolean isRegelementAkkoord() {
        return regelementAkkoord;
    }

    public Address getAdres() {
        return adres;
    }

    public void setAdres(Address adres) {
        this.adres = adres;
    }

    public void setRegelementAkkoord(boolean regelementAkkoord) {
        this.regelementAkkoord = regelementAkkoord;
    }

    public boolean isActiefAccount() {
        return actiefAccount;
    }

    public void setActiefAccount(boolean actiefAccount) {
        this.actiefAccount = actiefAccount;
    }

    public String getSaldo(){
        BigDecimal saldoB = portemonee.getBalans();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        df.setGroupingUsed(false);
        String uit = df.format(saldoB);
        String saldoS = uit+" euro";
        return saldoS;
    }

    @Override
    public String toString() {
        return "Gebruiker{" +
                "id=" + id +
                ", emailAdress='" + emailAdress + '\'' +
                '}';
    }
}
