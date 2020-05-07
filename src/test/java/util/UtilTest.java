package util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static util.Util.containsNONumber;
import static util.Util.isValidEmail;

class UtilTest {



    @Test
    void checkPassword() {
    }

    @Test
    void isValidEmailTest() {
        String[] testfirefalse = new String[]{"12344HHahdf", "g12344HHahdf", "", "hoi", "water", "1i", "verdraad.pun.punter.puntest@puntest", "testemailtest.com"};
        String[] testfiretrue = new String[]{"testemail@test.com", "verdraad.pun.punter.puntest@puntest.com", "MF.VAND.DEN.BOS@BELASTINGDIENST.NL"};
        for(String x : testfirefalse){
            assertThat(isValidEmail(x)).isEqualTo(false);
        }
        for(String x : testfiretrue){
            assertThat(isValidEmail(x)).isEqualTo(true);
        }
    }

    @Test
    void containsNoNumberTest(){
        ArrayList<Integer> target = new ArrayList<>();
        assertThat(containsNONumber("12344HHahdf")).isEqualTo(false);
        assertThat(containsNONumber("g12344HHahdf")).isEqualTo(false);
        assertThat(containsNONumber("")).isEqualTo(true);
        assertThat(containsNONumber("hoi")).isEqualTo(true);
        assertThat(containsNONumber("water")).isEqualTo(true);
        assertThat(containsNONumber("1i")).isEqualTo(false);
    }
}