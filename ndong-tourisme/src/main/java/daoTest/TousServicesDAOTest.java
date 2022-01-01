package daoTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dao.TousServicesDAO;

class TousServicesDAOTest {

    @Test
    void testEstUneActivite() {
        TousServicesDAO tousServicesDAO = new TousServicesDAO();
        assertTrue( tousServicesDAO.estUneActivite( Long.valueOf( "3" ) ) );
    }

}
