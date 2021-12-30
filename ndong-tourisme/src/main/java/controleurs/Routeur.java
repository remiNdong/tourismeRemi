package controleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HotelsDAO;
import dao.RestaurantsDAO;
import dao.TousServicesDAO;
import modeles.Hotel;
import modeles.Restaurant;
import modeles.Service;
import modelesWeb.EnsemblePage;

/**
 * Servlet implementation class Routeur
 */
@WebServlet( "/Routeur" )
public class Routeur extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String            VUES             = "/vues/";

    /**
     * @see HttpServlet#HttpServlet()
     */

    public Routeur() {

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String maVue = "";

        try {

            String action = request.getParameter( "action" );

            // lors de l'arrivee au routeur on sera toujours a la page 1 a
            // afficher
            int indexPage = 1;

            HttpSession httpSession = request.getSession();

            if ( action.equals( "Tous Les Services" ) ) {
                TousServicesDAO tousServicesDAO = new TousServicesDAO();

                String ville = request.getParameter( "ville" );
                // parametre de la fonction de preparations de requete Sql
                // pour la selection de Service on a un seul critere de
                // selection qui est la ville
                List<Object> valeurs = new ArrayList<Object>();

                if ( ville != null && !ville.equals( "" ) ) {
                    valeurs.add( ville );
                }

                List<Service> listToutServices = tousServicesDAO.selectionServices( valeurs );

                EnsemblePage<Service> ensemblePage = new EnsemblePage<Service>( listToutServices );

                // on recupere la page de l'indice indiqué
                // il y en a au moins une
                // lors de la page routeur on demandera systematiquement
                // l'indice 1
                List<Service> listServices = ensemblePage.getPage( indexPage );

                // on recupere la liste des villes a afficher dans le select de
                // la jsp
                List<String> listVilles = tousServicesDAO.listVilles();
                request.setAttribute( "villes", listVilles );

                httpSession.setAttribute( "ensemblePage", ensemblePage );
                request.setAttribute( "listePage", ensemblePage.getPages().keySet() );
                request.setAttribute( "listServices", listServices );

                maVue = VUES + "tousServices.jsp";

            } else if ( action.equals( "Hotels" ) ) {
                HotelsDAO hotelsDAO = new HotelsDAO();

                String ville = request.getParameter( "ville" );
                String etoileString = request.getParameter( "etoile" );
                Integer etoiles = 0;

                if ( etoileString != null )
                    etoiles = Integer.parseInt( etoileString );
                // parametres de la fonction de preparations de requete Sql
                // pour la selection d'hotel on a un deux criteres de
                // selection qui la ville et le classement en etoiles
                List<Object> valeurs = new ArrayList<Object>();
                List<String> attributs = new ArrayList<String>();
                List<String> selections = new ArrayList<String>();

                if ( ville != null && !ville.equals( "" ) ) {
                    valeurs.add( ville );
                    attributs.add( "adresse.ville" );
                    selections.add( "ville" );
                }

                if ( etoiles != 0 ) {
                    valeurs.add( etoiles );
                    attributs.add( "classement" );
                    selections.add( "classement" );

                }

                List<Hotel> listToutHotels = hotelsDAO.selectionHotels( attributs, selections, valeurs );

                EnsemblePage<Hotel> ensemblePage = new EnsemblePage<Hotel>( listToutHotels );

                // on recupere la page de l'indice indiqué
                // il y en a au moins une
                // lors de la page routeur on demandera systematiquement
                // l'indice 1
                List<Hotel> listHotels = ensemblePage.getPage( indexPage );

                // on recupere la liste des villes a afficher dans le select de
                // la jsp
                List<String> listVilles = hotelsDAO.listVilles();
                request.setAttribute( "villes", listVilles );

                // on recupere la liste des etoiles a afficher dans le select de
                // la jsp
                List<Integer> listEtoiles = hotelsDAO.listEtoiles();
                request.setAttribute( "etoiles", listEtoiles );

                httpSession.setAttribute( "ensemblePage", ensemblePage );
                request.setAttribute( "listePage", ensemblePage.getPages().keySet() );
                request.setAttribute( "listHotels", listHotels );

                maVue = VUES + "hotels.jsp";

            } else if ( action.equals( "Restaurants" ) ) {
                RestaurantsDAO restaurantsDAO = new RestaurantsDAO();

                String ville = request.getParameter( "ville" );
                String reservation = request.getParameter( "reservation" );
                String terrasse = request.getParameter( "terrasse" );

                // parametres de la fonction de preparations de requete Sql
                // pour la selection de restaurants on a un trois criteres de
                // selection qui la ville , la terrasse et la reservation
                List<Object> valeurs = new ArrayList<Object>();
                List<String> attributs = new ArrayList<String>();
                List<String> selections = new ArrayList<String>();

                if ( ville != null && !ville.equals( "" ) ) {
                    valeurs.add( ville );
                    attributs.add( "adresse.ville" );
                    selections.add( "ville" );
                }

                if ( reservation != null && !reservation.equals( "" ) ) {
                    valeurs.add( reservation );
                    attributs.add( "reservation" );
                    selections.add( "reservation" );
                }

                if ( terrasse != null && !terrasse.equals( "" ) ) {
                    valeurs.add( terrasse );
                    attributs.add( "terrasse" );
                    selections.add( "terrasse" );

                }

                List<Restaurant> listToutRestaurants = restaurantsDAO.selectionRestaurants( attributs, selections,
                        valeurs );

                EnsemblePage<Restaurant> ensemblePage = new EnsemblePage<Restaurant>( listToutRestaurants );

                // on recupere la page de l'indice indiqué
                // il y en a au moins une
                // lors de la page routeur on demandera systematiquement
                // l'indice 1
                List<Restaurant> listRestaurants = ensemblePage.getPage( indexPage );

                // on recupere la liste des villes a afficher dans le select de
                // la jsp
                List<String> listVilles = restaurantsDAO.listVilles();
                request.setAttribute( "villes", listVilles );

                httpSession.setAttribute( "ensemblePage", ensemblePage );
                request.setAttribute( "listePage", ensemblePage.getPages().keySet() );
                request.setAttribute( "listRestaurants", listRestaurants );

                maVue = VUES + "restaurants.jsp";

            }

        } catch ( Exception e ) {
            maVue = VUES + "exception.jsp";
            request.setAttribute( "message", e.getMessage() );
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher( maVue );
        dispatcher.forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet( request, response );
    }

}
