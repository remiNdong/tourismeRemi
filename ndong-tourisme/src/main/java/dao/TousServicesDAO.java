package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import configuration.ConfigurationBDD;
import modeles.Activite;
import modeles.Hotel;
import modeles.Restaurant;
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

    public Service findService( Long idService ) {

        Service service = session.get( Service.class, idService );

        if ( service == null )
            throw new RuntimeException( "L'identifiant " + idService + " ne correspond à aucun Service" );

        return service;

    }

    public Hotel findHotel( Long idService ) {

        Hotel service = session.get( Hotel.class, idService );

        if ( service == null )
            throw new RuntimeException( "L'identifiant " + idService + " ne correspond à aucun Hotel" );

        return service;

    }

    public Restaurant findRestaurant( Long idService ) {

        Restaurant service = session.get( Restaurant.class, idService );

        if ( service == null )
            throw new RuntimeException( "L'identifiant " + idService + " ne correspond à aucun Restaurant" );

        return service;

    }

    public Activite findActivite( Long idService ) {

        Activite service = session.get( Activite.class, idService );

        if ( service == null )
            throw new RuntimeException( "L'identifiant " + idService + " ne correspond à aucune Activité" );

        return service;

    }

    public boolean estUnHotel( Long idService ) {

        Query q = session
                .createQuery(
                        " select count(*) as nbHotel from Hotel where id=:idService" );

        q.setLong( "idService", idService );
        Long resultat = (Long) q.uniqueResult();

        // on verifie si l'idService est present dans la table Hotel

        if ( resultat == 0 )
            return false;
        else
            return true;

    }

    public boolean estUnRestaurant( Long idService ) {

        Query q = session
                .createQuery(
                        " select count(*) as nbRestaurant  from Restaurant where id=:idService" );

        q.setLong( "idService", idService );
        Long resultat = (Long) q.uniqueResult();

        // on verifie si l'idService est present dans la table Restaurant

        if ( resultat == 0 )
            return false;
        else
            return true;

    }

    public boolean estUneActivite( Long idService ) {

        Query q = session
                .createQuery(
                        " select count(*) as nbActivite from Activite where id=:idService" );

        q.setLong( "idService", idService );
        Long resultat = (Long) q.uniqueResult();

        // on verifie si l'idService est present dans la table Activite

        if ( resultat == 0 )
            return false;
        else
            return true;

    }

}
