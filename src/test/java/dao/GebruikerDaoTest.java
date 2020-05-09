package dao;

import controllers.MainController;
import controllers.RegistreerController;
import dao.exceptions.GebruikerNotFoundException;
import domain.Gebruiker;
import factories.GebruikerType;
import controllers.HoofdMenuNietIngelogdController;
import frontend.console.RegistreerDialog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.ScannerWrapper;

import javax.persistence.EntityManager;

import static java.time.Duration.ofMillis;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GebruikerDaoTest {

    @Mock
    ScannerWrapper scannerMock;

    MainController mainController;

    RegistreerController testDialog;

    HoofdMenuNietIngelogdController terug;

    GebruikerDao testDao;

    @BeforeEach
    void setup(){
        mainController = MainController.getInstance();
        EntityManager em = mainController.getEntityManager();
        mainController.setScanner(scannerMock);
        terug = new HoofdMenuNietIngelogdController();
        testDialog = new RegistreerController(GebruikerType.BEZOEKER, terug);
        testDao = new GebruikerDao(em);
    }

    @Test
    void loadTestGreenRun() {

    }

    @Test
    void selectWithEmail() {

        assertTimeout(ofMillis(2000), () -> {
            when(scannerMock.nextLine()).thenReturn("testemail@test.com", "testemail@test.com", "testWacht1", "testWacht1");
            testDialog.load();
            verify(scannerMock, times(4)).nextLine();
        });
        try {
            Gebruiker testGebruiker = testDao.selectWithEmail("testemail@test.com");
            assertThat(testGebruiker.verifyPassword("testWacht1"));
        } catch (GebruikerNotFoundException e) {
            e.printStackTrace();
        }



    }


}