package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import configuration.ConfigurationBDD;
import modeles.Hotel;
import modeles.Service;
import utils.SqlUtils;

public class HotelsDAO {

    /**
     * Objet Session de Hibernate
     */
    private Session          session;
    private ConfigurationBDD configurationBDD;

    public HotelsDAO() {

        configurationBDD = new ConfigurationBDD();

        ServiceRegistry serviceRegistry = configurationBDD.getServiceRegistry();
        Configuration configuration = configurationBDD.getConfiguration();
        SessionFactory sessionFactory = configuration.buildSessionFactory( serviceRegistry );
        session = sessionFactory.openSession();
    }

    /*
     * Methode qui sera utilisé pour selectionner la ville du Service et le
     * nombre d'etoiles et filtrer
     */

    public List<Hotel> selectionHotels( List<String> attributs, List<String> selections, List<Object> valeurs ) {

        String table = "Hotel";

        Query q = SqlUtils.prepareSQL( table, attributs,
                selections, valeurs, session );

        List<Hotel> hotels = q.list();
        if ( hotels.size() == 0 )
            throw new RuntimeException( "Aucun Hotel trouvé" );

        return hotels;

    }

    public List<Service> listeHotels() {

        Query q = session.createQuery( "from Hotel " );

        List<Service> services = q.list();
        if ( services.size() == 0 )
            throw new RuntimeException( "Aucun Hotel trouvé" );

        return services;
    }

    public Hotel findHotel( Long idHotel ) {

        Hotel hotel = session.get( Hotel.class, idHotel );

        if ( hotel == null )
            throw new RuntimeException( "L'identifiant " + idHotel + " ne correspond à aucun Hotel" );

        return hotel;

    }

    public List<String> listVilles() {

        Query q = session.createQuery( "select distinct hotel.adresse.ville from Hotel as hotel" );

        List<String> listVilles = q.list();
        if ( listVilles.size() == 0 )
            throw new RuntimeException( "Aucun hotel trouvé" );

        return listVilles;

    }

    public List<Integer> listEtoiles() {

        Query q = session
                .createQuery( "select distinct hotel.classement from Hotel as hotel order by hotel.classement asc" );

        List<Integer> listEtoiles = q.list();
        if ( listEtoiles.size() == 0 )
            throw new RuntimeException( "Aucun hotel trouvé" );

        return listEtoiles;

    }

}
