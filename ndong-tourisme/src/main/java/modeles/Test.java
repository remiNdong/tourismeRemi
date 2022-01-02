package modeles;

import dao.TousServicesDAO;

public class Test {

    public static void main( String[] args ) throws Exception {
        TousServicesDAO tsDAO = new TousServicesDAO();
        Service service = tsDAO.findService( Long.valueOf( 22 ) );

        for ( Notation n : service.getNotationsTriees() )
            System.out.println( n.getId().intValue() );

    }

}
