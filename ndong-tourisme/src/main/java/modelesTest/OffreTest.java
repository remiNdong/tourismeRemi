package modelesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import modeles.Activite;
import modeles.Offre;

class OffreTest {

    @Test
    void testCalculDuPrixFinal() {

        Offre offre = new Offre();
        offre.setPrix( 100.00 );
        Activite catacombes = new Activite();
        catacombes.setMargeNegociee( 5 );
        offre.setPrestataire( catacombes );
        assertEquals( 95, offre.calculDuPrixFinal( "Activite" ) );

    }

}
