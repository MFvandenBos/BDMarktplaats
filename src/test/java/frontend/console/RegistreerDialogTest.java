package frontend.console;

import controllers.HoofdMenuNietIngelogdController;
import controllers.MainController;
import factories.GebruikerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.ScannerWrapper;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistreerDialogTest {

    @Mock
    ScannerWrapper scannerMock;

    MainController mainController;

    RegistreerDialog testDialog;

    HoofdMenuNietIngelogdController terug;

    @BeforeEach
    void setup(){
        mainController = MainController.getInstance();
        mainController.setScanner(scannerMock);
        terug = new HoofdMenuNietIngelogdController();
        testDialog = new RegistreerDialog(GebruikerType.BEZOEKER, terug);
    }

    @Test
    void loadTestGreenRun() {
        assertTimeout(ofMillis(2000), () -> {
            when(scannerMock.nextLine()).thenReturn("testemail@test.com", "testemail@test.com", "testWacht1", "testWacht1");
            testDialog.load();
            verify(scannerMock, times(4)).nextLine();
        });
    }

    @Test
    void testLoad() {
    }
}