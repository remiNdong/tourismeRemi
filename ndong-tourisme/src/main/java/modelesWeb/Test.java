package modelesWeb;

import java.util.ArrayList;
import java.util.List;

import modeles.Service;

public class Test {

    public static void main( String[] args ) {

        Service serv1 = new Service();
        Service serv2 = new Service();
        Service serv3 = new Service();
        List<Service> listServices = new ArrayList<Service>();
        listServices.add( serv1 );
        listServices.add( serv2 );
        listServices.add( serv3 );
        EnsemblePage<Service> ensemblePage = new EnsemblePage<Service>( listServices );
        int nbList = ensemblePage.getPages().keySet().size();
        List<Service> listServ = ensemblePage.getPage( 50 );
        System.out.println( listServ );

    }

}
