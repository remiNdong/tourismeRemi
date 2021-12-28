package modelesWebTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import modeles.Service;
import modelesWeb.EnsemblePage;

class EnsemblePageTest {

    @Test
    void testCreation() {

        Service serv1 = new Service();
        serv1.setId( new Long( "1" ) );
        Service serv2 = new Service();
        serv1.setId( new Long( "2" ) );
        Service serv3 = new Service();
        serv1.setId( new Long( "3" ) );
        List<Service> listServices = new ArrayList<Service>();
        listServices.add( serv1 );
        listServices.add( serv2 );
        listServices.add( serv3 );
        EnsemblePage<Service> ensemblePage = new EnsemblePage<Service>( listServices );
        int nbList = ensemblePage.getPages().keySet().size();
        assertEquals( 1, nbList );

        List<Service> listBis = ensemblePage.getPage( 1 );
        assertEquals( 3, listBis.size() );
        Service serv4 = listBis.get( 0 );
        Service serv5 = listBis.get( 1 );
        Service serv6 = listBis.get( 2 );
        assertEquals( serv1, serv4 );
        assertEquals( serv2, serv5 );
        assertEquals( serv3, serv6 );

    }

    @Test
    void testnbPages() {

        List<Service> listServices = new ArrayList<Service>();

        for ( int i = 0; i < 25; i++ ) {
            listServices.add( new Service() );
            listServices.get( i ).setId( new Long( i + "" ) );

        }
        EnsemblePage<Service> ensemblePage = new EnsemblePage<Service>( listServices );
        int nbList = ensemblePage.getPages().keySet().size();
        // il doit y avoir 3 pages
        assertEquals( 3, nbList );
        List<Service> page3 = ensemblePage.getPage( 3 );
        // il doit rester seulement 5 service a la page 3
        assertEquals( 5, page3.size() );
    }

}
