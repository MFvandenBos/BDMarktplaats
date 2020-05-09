package controllers;

import domain.Gebruiker;
import domain.exceptions.InvalidEmailException;
import domain.exceptions.InvalidPasswordException;
import factories.GebruikerType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.ScannerWrapper;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegelsControllerTest {
    Gebruiker testGebruiker;

    RegelsController testSubject;


    @Mock
    ScannerWrapper scannerMock;

    MainController mainController;

    RegistreerController testDialog;

    HoofdMenuNietIngelogdController terug;

    @BeforeEach
    void setup() throws InvalidEmailException {
        terug = new HoofdMenuNietIngelogdController();
        testDialog = new RegistreerController(GebruikerType.BEZOEKER, terug );
        testGebruiker = new Gebruiker("testspecial@special.com");
        testSubject = new RegelsController(testDialog);
        mainController = MainController.getInstance();
        mainController.setScanner(scannerMock);
    }



    @Test
    void loadTest() {
        NotImplementedException e = Assertions.assertThrows(NotImplementedException.class, () -> testSubject.load());
    }

    @Test
    void testLoadGebruikerJa() {
        assertTimeout(ofMillis(2000), () -> {
            when(scannerMock.nextLine()).thenReturn("Ja");
            String ok = scannerMock.nextLine();
            testSubject.load(testGebruiker);
            verify(scannerMock, times(2)).nextLine();
        });
    }

    @Test
    void testLoadGebruikerJ() {
        assertTimeout(ofMillis(2000), () -> {
            when(scannerMock.nextLine()).thenReturn("J");
            testSubject.load(testGebruiker);
            verify(scannerMock, times(1)).nextLine();
        });
    }

    @Test
    void testLoadGebruikerj() {
        assertTimeout(ofMillis(2000), () -> {
            when(scannerMock.nextLine()).thenReturn("j");
            testSubject.load(testGebruiker);
            verify(scannerMock, times(1)).nextLine();
        });
    }

}