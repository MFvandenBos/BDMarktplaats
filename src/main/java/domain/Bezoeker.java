package domain;

import domain.exceptions.InvalidPasswordException;
import domain.exceptions.NoKnownAddressException;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Bezoeker extends Gebruiker {
    boolean thuisAfhalen;
    boolean magazijnAfhalen;
    boolean versturen;
    boolean versturenRembours;

    @Embedded
    Address adres;

    public Bezoeker(){}

    public Bezoeker(String email, String password, boolean thuisAfhalen, boolean magazijnAfhalen, boolean versturen, boolean versturenRembours, Address adres) throws InvalidPasswordException {
        super(email, password);
        this. versturenRembours = versturenRembours;
        this.versturen = versturen;
        this.magazijnAfhalen = magazijnAfhalen;
        this.adres = adres;
        if(adres == null){
            this.thuisAfhalen = false;
        }else{
            this.thuisAfhalen = thuisAfhalen;
        }

    }

    public boolean isMagazijnAfhalen() {
        return magazijnAfhalen;
    }

    public void setMagazijnAfhalen(boolean magazijnAfhalen) {
        this.magazijnAfhalen = magazijnAfhalen;
    }

    public boolean isVersturen() {
        return versturen;
    }

    public void setVersturen(boolean versturen) {
        this.versturen = versturen;
    }

    public boolean isVersturenRembours() {
        return versturenRembours;
    }

    public void setVersturenRembours(boolean versturenRembours) {
        this.versturenRembours = versturenRembours;
    }

    public Address getAdres() {
        return adres;
    }

    public void setAdres(Address adres) {
        this.adres = adres;
    }

    public boolean isThuisAfhalen() {
        return thuisAfhalen;
    }

    public void setThuisAfhalen(boolean thuisAfhalen) throws NoKnownAddressException {
        if(adres == null){
            throw new NoKnownAddressException("geen adres beschikbaar");
        }else{
            this.thuisAfhalen = thuisAfhalen;
        }
    }
}
