package frontend.console;

import controllers.HoofdMenuNietIngelogdController;
import controllers.MainController;
import controllers.RegistreerController;
import domain.exceptions.InvalidPasswordException;
import factories.GebruikerType;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.ScannerWrapper;

import static java.time.Duration.ofMillis;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistreerDialogTest {

    @Mock
    ScannerWrapper scannerMock;

    MainController mainController;

    RegistreerController testDialog;

    HoofdMenuNietIngelogdController terug;



    @BeforeEach
    void setup(){
        mainController = MainController.getInstance();
        mainController.setScanner(scannerMock);
        terug = new HoofdMenuNietIngelogdController();
        testDialog = new RegistreerController(GebruikerType.BEZOEKER, terug);
    }

    @Test
    void loadTestGreenRun() {
        assertTimeout(ofMillis(50000), () -> {
            when(scannerMock.nextLine()).thenReturn("testemail@test.com", "testemail@test.com", "testWacht1", "testWacht1","J","N","N","N","testWacht1","41");
            RuntimeException e = Assertions.assertThrows(RuntimeException.class, () -> testDialog.load());
            assertThat(e.getMessage()).isEqualTo("test exit!");
            verify(scannerMock, times(10)).nextLine();
        });
    }

    @Test
    void testLoad() {
    }
}