package domain;

import domain.exceptions.InvalidPasswordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;

class GebruikerTest {

    Gebruiker testGebruiker;

    @BeforeEach
    void setup (){
        testGebruiker = new Gebruiker();
    }

    @Test
    void setEmailAdress() {
    }

    @Test
    void setPasswordmetSpaties() throws InvalidPasswordException {
        testGebruiker.setPassword("Hoi ik ben Michael en 31 jaar");
    }

    @Test
    void setPasswordThrowsAtShortPassword() {
        InvalidPasswordException e = Assertions.assertThrows(InvalidPasswordException.class, () -> testGebruiker.setPassword("kort"));
    }
    @Test
    void setPasswordThrowsAtNoNumberPassword() {
        InvalidPasswordException e = Assertions.assertThrows(InvalidPasswordException.class, () -> testGebruiker.setPassword("lang_zonder_nummer"));
    }
    @Test
    void setPasswordThrowsAtEmailPassword() {
        testGebruiker.setEmailAdress("voorbeeldemail@gmail.com");
        InvalidPasswordException e = Assertions.assertThrows(InvalidPasswordException.class, () -> testGebruiker.setPassword("voorbeeldemail@gmail.com"));
    }

    @Test
    void containsNoNumberTest(){
        ArrayList<Integer> target = new ArrayList<>();
        assertThat(testGebruiker.containsNONumber("12344HHahdf")).isEqualTo(false);
        assertThat(testGebruiker.containsNONumber("g12344HHahdf")).isEqualTo(false);
        assertThat(testGebruiker.containsNONumber("")).isEqualTo(true);
        assertThat(testGebruiker.containsNONumber("hoi")).isEqualTo(true);
        assertThat(testGebruiker.containsNONumber("water")).isEqualTo(true);
        assertThat(testGebruiker.containsNONumber("1i")).isEqualTo(false);
    }

    @Test
    void verifyPasswordTest()  {
        String vbPW = "voorbeeldPassword1";
        String vbPW2 = "voorbeeldPassword2";
        try {
            testGebruiker.setPassword(vbPW);
        }catch (InvalidPasswordException ex){
            assertThat("setPassword threw InvalidPasswordException").isEqualTo("which is not supposed to happen");
        }
        assertThat(testGebruiker.verifyPassword(vbPW)).isEqualTo(true);
        assertThat(testGebruiker.verifyPassword(vbPW2)).isEqualTo(false);
    }
}