package configuration;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    private static Configuration   configuration;
    private static ServiceRegistry serviceRegistry;
    private static Session         session;

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
        SessionFactory sessionFactory = configuration.buildSessionFactory( serviceRegistry );
        session = sessionFactory.openSession();

    }

    /* Méthode chargée de fournir une connexion à la base de données */
    /* package */
    public static Session getSession() throws SQLException {

        if ( session == null )
            new ConfigurationBDD();

        return session;
    }

}
