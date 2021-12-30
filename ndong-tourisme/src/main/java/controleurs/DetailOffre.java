package controleurs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OffresDAO;
import dao.TousServicesDAO;
import modeles.Offre;
import modeles.Service;

/**
 * Servlet implementation class DetailOffre
 */
@WebServlet( "/DetailOffre" )
public class DetailOffre extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String            VUES             = "/vues/";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailOffre() {

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String maVue = VUES;

        String idOffreString = request.getParameter( "idOffre" );

        try {
            Long idOffre = Long.parseLong( idOffreString );

            OffresDAO offresDAO = new OffresDAO();

            Offre offre = offresDAO.findOffre( idOffre );
            request.setAttribute( "offre", offre );

            // il va falloir savoir de quel type est notre service pour calculer
            // le prix final de l'offre
            TousServicesDAO tousServicesDAO = new TousServicesDAO();
            Service service = offre.getPrestataire();

            Double prixOffre = 0.00;

            if ( tousServicesDAO.estUnHotel( service.getId() ) )
                prixOffre = offre.calculDuPrixFinal( "Hotel" );
            else if ( tousServicesDAO.estUnRestaurant( service.getId() ) )
                prixOffre = offre.calculDuPrixFinal( "Restaurant" );
            else if ( tousServicesDAO.estUnRestaurant( service.getId() ) )
                prixOffre = offre.calculDuPrixFinal( "Activite" );

            request.setAttribute( "prixOffre", prixOffre );

            maVue = VUES + "detailOffre.jsp";

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
