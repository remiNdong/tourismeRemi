package utils;

import java.util.List;

import dao.TousServicesDAO;
import modeles.Service;

public class Test {

    public static void main( String[] args ) throws NoSuchMethodException, SecurityException {

        TousServicesDAO tousServicesDAO = new TousServicesDAO();
        List<Service> listToutServices = tousServicesDAO.selectionServices();
        // List<Service> listToutServices = tousServicesDAO.listeServices();

        for ( Service s : listToutServices )
            System.out.println( s.getNom() );

    }

}
