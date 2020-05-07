package domain;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Portemonee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    //TODO: add log

    private BigDecimal balans;


    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    Gebruiker gebruiker;

    public Portemonee(){
        balans = new BigDecimal("0.0");
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }
}
