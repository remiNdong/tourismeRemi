package utils;

import java.util.Arrays;
import java.util.List;

import dao.RestaurantsDAO;
import modeles.Restaurant;

public class Test {

    public static void main( String[] args ) throws NoSuchMethodException, SecurityException {

        RestaurantsDAO restaurantsDAO = new RestaurantsDAO();

        List<Object> valeurs = Arrays.asList( "Paris" );
        List<String> attributs = Arrays.asList( "adresse.ville" );
        List<String> selection = Arrays.asList( "ville" );

        List<Restaurant> liste = restaurantsDAO.selectionRestaurants( null, null, null );

    }
}
