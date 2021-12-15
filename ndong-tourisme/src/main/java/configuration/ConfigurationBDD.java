package configuration;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import modeles.Activite;
import modeles.Hotel;
import modeles.Internaute;
import modeles.Notation;
import modeles.Offre;
import modeles.Restaurant;
import modeles.Service;

/**
 * Classe servant a declarer toutes les classes persistantes et à recuperer une
 * configuration
 */

public class ConfigurationBDD {

    private Configuration   configuration;
    private ServiceRegistry serviceRegistry;

    public ConfigurationBDD() {

        configuration = new Configuration().configure( "/hibernate.cfg.xml" );

        // ICI ON AJOUTE LES CLASSES JPA
        configuration.addAnnotatedClass( Activite.class );
        configuration.addAnnotatedClass( Hotel.class );
        configuration.addAnnotatedClass( Internaute.class );
        configuration.addAnnotatedClass( Notation.class );
        configuration.addAnnotatedClass( Offre.class );
        configuration.addAnnotatedClass( Restaurant.class );
        configuration.addAnnotatedClass( Service.class );

        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings( configuration.getProperties() ).build();

    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

}
