package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import configuration.ConfigurationBDD;
import modeles.Service;

public class TousServicesDAO {

    /**
     * Objet Session de Hibernate
     */
    private Session          session;
    private ConfigurationBDD configurationBDD;

    public TousServicesDAO() {

        configurationBDD = new ConfigurationBDD();

        ServiceRegistry serviceRegistry = configurationBDD.getServiceRegistry();
        Configuration configuration = configurationBDD.getConfiguration();
        SessionFactory sessionFactory = configuration.buildSessionFactory( serviceRegistry );
        session = sessionFactory.openSession();
    }

    public List<Service> listeServices() {

        Query q = session
                .createQuery( "from Service " );

        List<Service> services = q.list();
        if ( services.size() == 0 )
            throw new RuntimeException( "Aucun Service trouvé" );

        return services;
    }

}
