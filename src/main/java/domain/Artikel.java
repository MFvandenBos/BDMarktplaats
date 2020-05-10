package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Artikel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    @NotNull
    String naam;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    Gebruiker verkoper;

    BigDecimal prijs;

    @ManyToOne(optional = false)
    @JoinColumn(name="Category_ID")
    Categorie categorie;

    public Artikel(){}

    public Artikel(String naam, Categorie categorie, String prijs, Gebruiker verkoper){
        this.naam = naam;
        setCategorie(categorie);
        setPrijs(new BigDecimal(prijs));
        setVerkoper(verkoper);
    }

    public Gebruiker getVerkoper() {
        return verkoper;
    }

    public void setVerkoper(Gebruiker verkoper) {
        this.verkoper = verkoper;
        verkoper.plaatsArtikel(this);
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
