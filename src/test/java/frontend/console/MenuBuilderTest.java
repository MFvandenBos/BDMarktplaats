package frontend.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MenuBuilderTest {

    MenuBuilder testSubject;

    @BeforeEach
    void setup(){
        testSubject = new MenuBuilder();
    }


    @Test
    void createMenuHeader() {

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

        String result = testSubject.createMenuHeader();

        StringBuilder expected = new StringBuilder("");
        expected.append("*************************").append(System.lineSeparator());

        assertThat(result).isEqualTo(expected.toString());
    }


    @Test
    void createMenuHeaderlong() {

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
        String result = testSubject.createMenuHeader("Woef" );

        StringBuilder expected = new StringBuilder("");
        expected.append("*************************").append(System.lineSeparator());
        expected.append("*          Woef         *").append(System.lineSeparator());
        expected.append("*************************").append(System.lineSeparator());

        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    void createBorderedOutputTest(){
        String text1 = "Dit is een lange test zin, langer dan 66 tekens. Daarom zou het moeten worden verspreid. ";
        String text2 = "Dit is een korte test zin.";
        String result = testSubject.createBorderedOutput("-","|", text1, text2);
        System.out.println(result);


    }

    @Test
    void twoColumsTest(){
        String[] col1 = new String[]{"column 1 veld 1", "column 1 veld 2"};
        String[] col2 = new String[]{"column 2 waarde 1", "column 2 waarde 2"};
        String result = testSubject.toonTwoColumns(col1, col2, ":");
        StringBuilder expected = new StringBuilder("");
        System.out.println(result);
        expected.append("column 1 veld 1:                        column 2 waarde 1             ").append(System.lineSeparator());
        expected.append("column 1 veld 2:                        column 2 waarde 2             ").append(System.lineSeparator());
        System.out.println(expected.toString());
        assertThat(result).isEqualTo(expected.toString());

    }


}