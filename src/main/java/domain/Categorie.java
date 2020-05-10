package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Categorie extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    @NotNull
    @Column(unique = true)
    String naam;

    @NotNull
    Soort soort;

    public Categorie(){}

    public Categorie(String naam, Soort soort){
        setNaam(naam);
        setSoort(soort);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        naam = naam.toLowerCase();
        this.naam = naam;
    }

    public Soort getSoort() {
        return soort;
    }

    public void setSoort(Soort soort) {
        this.soort = soort;
    }
}
