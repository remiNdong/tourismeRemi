package utils;

import java.util.TreeMap;

public class Test {

    public static void main( String[] args ) throws NoSuchMethodException, SecurityException {

        /*
         * 
         * TousServicesDAO tousServicesDAO = new TousServicesDAO(); List<Object>
         * valeurs = Arrays.asList( "Juan les pins" ); // List<Object> valeurs =
         * null; List<Service> listToutServices =
         * tousServicesDAO.selectionServices( valeurs ); // List<Service>
         * listToutServices = tousServicesDAO.listeServices();
         * 
         * for ( Service s : listToutServices ) System.out.println( s.getNom()
         * );
         */

        TreeMap<Integer, String> ts = new TreeMap();
        ts.put( 1, "ok" );
        ts.put( 2, "ko" );
        for ( Integer i : ts.keySet() )
            System.out.println( i );

    }

}
