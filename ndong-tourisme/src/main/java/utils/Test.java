package utils;

import java.util.Arrays;
import java.util.List;

import dao.TousServicesDAO;
import modeles.Service;

public class Test {

    public static void main( String[] args ) throws NoSuchMethodException, SecurityException {

        TousServicesDAO tousServicesDAO = new TousServicesDAO();
        List<Object> valeurs = Arrays.asList( "Juan les pins" );
        // List<Object> valeurs = null;
        List<Service> listToutServices = tousServicesDAO.selectionServices(
                valeurs );
        // List<Service> listToutServices = tousServicesDAO.listeServices();

        for ( Service s : listToutServices )
            System.out.println( s.getNom() );

    }

}
