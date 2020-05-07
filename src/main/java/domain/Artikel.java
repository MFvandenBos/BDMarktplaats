package domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Artikel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    Gebruiker verkoper;

    BigDecimal prijs;


}
