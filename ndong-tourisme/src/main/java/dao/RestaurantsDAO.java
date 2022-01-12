package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import configuration.ConfigurationBDD;
import modeles.Restaurant;
import utils.SqlUtils;

/**
 * Objet qui permet de faire les transactions concernant les Restaurants avec la
 * base de données
 */
public class RestaurantsDAO {

    /**
     * Objet Session de Hibernate
     */
    private Session session;

    public RestaurantsDAO() {

        try {

            session = ConfigurationBDD.getSession();

        } catch ( Exception e ) {
            throw new RuntimeException( "problème de connexion à la base de données" );
        }
    }

    /**
     * Methode qui sera utilisé pour selectionner un restaurant selon la ville ,
     * la posibilité de reserver et la terrasse
     */

    public List<Restaurant> selectionRestaurants( List<String> attributs, List<String> selections,
            List<Object> valeurs ) {

        String table = "Restaurant";

        Query q = SqlUtils.prepareSQL( table, attributs,
                selections, valeurs, session );

        List<Restaurant> restaurants = q.list();
        if ( restaurants.size() == 0 )
            throw new RuntimeException( "Aucun restaurant trouvé" );

        return restaurants;

    }

    public List<Restaurant> listeRestaurants() {

        Query q = session.createQuery( "from Restaurant " );

        List<Restaurant> restaurants = q.list();
        if ( restaurants.size() == 0 )
            throw new RuntimeException( "Aucun restaurant trouvé" );

        return restaurants;
    }

    public Restaurant findRestaurant( Long idRestaurant ) {

        Restaurant restaurant = session.get( Restaurant.class, idRestaurant );

        if ( restaurant == null )
            throw new RuntimeException( "L'identifiant " + idRestaurant + " ne correspond à aucun Restaurant " );

        return restaurant;

    }

    public List<String> listVilles() {

        Query q = session.createQuery( "select distinct restaurant.adresse.ville from Restaurant as restaurant" );

        List<String> listVilles = q.list();
        if ( listVilles.size() == 0 )
            throw new RuntimeException( "Aucun restaurant trouvé" );

        return listVilles;

    }

}
