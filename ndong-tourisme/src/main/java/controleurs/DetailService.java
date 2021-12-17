package controleurs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TousServicesDAO;
import modeles.Activite;
import modeles.Hotel;
import modeles.Restaurant;

/**
 * Servlet implementation class TousServices
 */
@WebServlet( "/DetailService" )
public class DetailService extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String            VUES             = "/vues/";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailService() {

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String maVue = VUES;
        // On devrait récupérer l'action requise par l'utilisateur
        String idServiceString = request.getParameter( "idService" );

        try {
            Integer idService = Integer.parseInt( idServiceString );

            TousServicesDAO tousServicesDAO = new TousServicesDAO();

            if ( tousServicesDAO.estUnHotel( idService ) ) {
                Hotel hotel = tousServicesDAO.findHotel( idService );
                request.setAttribute( "hotel", hotel );

            } else if ( tousServicesDAO.estUnRestaurant( idService ) ) {
                Restaurant restaurant = tousServicesDAO.findRestaurant( idService );
                request.setAttribute( "restaurant", restaurant );

            } else if ( tousServicesDAO.estUneActivite( idService ) ) {
                Activite activite = tousServicesDAO.findActivite( idService );
                request.setAttribute( "activite", activite );

            }

            maVue = VUES + "detailService.jsp";

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
