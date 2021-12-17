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

    public Service findService( Integer idService ) {

        Service service = session.get( Service.class, idService );

        if ( service == null )
            throw new RuntimeException( "L'identifiant " + idService + " ne correspond à aucun Service" );

        return service;

    }

    public Hotel findHotel( Integer idService ) {

        Hotel service = session.get( Hotel.class, idService );

        if ( service == null )
            throw new RuntimeException( "L'identifiant " + idService + " ne correspond à aucun Hotel" );

        return service;

    }

    public Restaurant findRestaurant( Integer idService ) {

        Restaurant service = session.get( Restaurant.class, idService );

        if ( service == null )
            throw new RuntimeException( "L'identifiant " + idService + " ne correspond à aucun Restaurant" );

        return service;

    }

    public Activite findActivite( Integer idService ) {

        Activite service = session.get( Activite.class, idService );

        if ( service == null )
            throw new RuntimeException( "L'identifiant " + idService + " ne correspond à aucune Activité" );

        return service;

    }

    public boolean estUnHotel( Integer idService ) {

        Query q = session
                .createQuery(
                        " select count(*)  from Hotel where id=:idService" );

        List<Object[]> result = q.list();
        q.setInteger( "idService", idService );

        // on verifie si l'idService est present dans la table Hotel
        Integer nbHotel = (Integer) result.get( 0 )[0];

        if ( nbHotel > 0 )
            return true;
        else
            return false;

    }

    public boolean estUnRestaurant( Integer idService ) {

        Query q = session
                .createQuery(
                        " select count(*)  from Restaurant where id=:idService" );

        List<Object[]> result = q.list();
        q.setInteger( "idService", idService );

        // on verifie si l'idService est present dans la table Hotel
        Integer nbRestaurant = (Integer) result.get( 0 )[0];

        if ( nbRestaurant > 0 )
            return true;
        else
            return false;

    }

    public boolean estUneActivite( Integer idService ) {

        Query q = session
                .createQuery(
                        " select count(*)  from Activite where id=:idService" );

        List<Object[]> result = q.list();
        q.setInteger( "idService", idService );

        // on verifie si l'idService est present dans la table Hotel
        Integer nbActivite = (Integer) result.get( 0 )[0];

        if ( nbActivite > 0 )
            return true;
        else
            return false;

    }

}
