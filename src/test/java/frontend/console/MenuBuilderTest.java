package frontend.console;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MenuBuilderTest {

    @Test
    void createMenuHeader() {
        MenuBuilder testSubject = new MenuBuilder();
        String result = testSubject.createMenuHeader("HOI", "dit is een test");

        StringBuilder expected = new StringBuilder("");
        expected.append("*************************").append(System.lineSeparator());
        expected.append("*          HOI          *").append(System.lineSeparator());
        expected.append("*    dit is een test    *").append(System.lineSeparator());
        expected.append("*************************").append(System.lineSeparator());
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    void createMenuHeaderEmpty() {
        MenuBuilder testSubject = new MenuBuilder();
        String result = testSubject.createMenuHeader();

        StringBuilder expected = new StringBuilder("");
        expected.append("*************************").append(System.lineSeparator());

        assertThat(result).isEqualTo(expected.toString());
    }


    @Test
    void createMenuHeaderlong() {
        MenuBuilder testSubject = new MenuBuilder();
        String result = testSubject.createMenuHeader("HOI", "dit is een lange test");

        StringBuilder expected = new StringBuilder("");
        expected.append("*******************************").append(System.lineSeparator());
        expected.append("*             HOI             *").append(System.lineSeparator());
        expected.append("*    dit is een lange test    *").append(System.lineSeparator());
        expected.append("*******************************").append(System.lineSeparator());

        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    void createMenuHeaderEven() {
        MenuBuilder testSubject = new MenuBuilder();
        String result = testSubject.createMenuHeader("Woef" );

        StringBuilder expected = new StringBuilder("");
        expected.append("*************************").append(System.lineSeparator());
        expected.append("*          Woef         *").append(System.lineSeparator());
        expected.append("*************************").append(System.lineSeparator());

        assertThat(result).isEqualTo(expected.toString());
    }


}