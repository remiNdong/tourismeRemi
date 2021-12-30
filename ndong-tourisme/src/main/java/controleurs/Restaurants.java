package controleurs;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RestaurantsDAO;
import modeles.Restaurant;
import modelesWeb.EnsemblePage;

/**
 * Servlet implementation class Restaurants
 */
@WebServlet( "/Restaurants" )
public class Restaurants extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String            VUES             = "/vues/";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Restaurants() {

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String maVue = VUES;

        try {

            RestaurantsDAO restaurantsDAO = new RestaurantsDAO();

            // il faudra d'abord trouver l'index de la page des resutat a
            // afficher
            int indexPage;

            HttpSession httpSession = request.getSession();

            indexPage = Integer.parseInt( request.getParameter( "indexPage" ) );

            // on recupere l'objet Page
            EnsemblePage<Restaurant> ensemblePage = (EnsemblePage<Restaurant>) httpSession
                    .getAttribute( "ensemblePage" );

            // on recupere la page de l'indice indiqué
            List<Restaurant> listRestaurants = ensemblePage.getPage( indexPage );

            httpSession.setAttribute( "ensemblePage", ensemblePage );
            request.setAttribute( "listePage", ensemblePage.getPages().keySet() );
            request.setAttribute( "listRestaurants", listRestaurants );

            // on recupere la liste des villes a afficher dans le select de
            // la jsp
            List<String> listVilles = restaurantsDAO.listVilles();
            request.setAttribute( "villes", listVilles );

            maVue = VUES + "restaurants.jsp";

        } catch (

        Exception e ) {
            maVue = VUES + "exception.jsp";
            request.setAttribute( "message", e.getMessage() );
        }

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher( maVue );
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