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

import dao.TousServicesDAO;
import modeles.Offre;
import modeles.Service;
import modelesWeb.EnsemblePage;

/**
 * Servlet implementation class Offres. Les Offres seront contenus dans un objet
 * EnsemblePage<Offre> lors du passage par ce controleur on sera toujours
 * renvoyé vers la 1ere page de cet ensemble pour les offres d'une entité de
 * Service
 */
@WebServlet( "/Offres" )
public class Offres extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String            VUES             = "/vues/";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Offres() {

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String maVue = VUES;

        try {

            String action = request.getParameter( "action" );

            String idServiceString = request.getParameter( "idService" );

            if ( action.equals( "listeOffres" ) ) {

                Long idService = Long.parseLong( idServiceString );

                TousServicesDAO tousServicesDAO = new TousServicesDAO();

                Service service = tousServicesDAO.findService( idService );

                EnsemblePage<Offre> ensemblePage = new EnsemblePage<Offre>(
                        new ArrayList( service.getOffresProposees() ) );

                // en passant par ce controleur on renverra toujours a la
                // premiere page des offres du service
                List<Offre> listeOffres = ensemblePage.getPage( 1 );

                HttpSession httpSession = request.getSession();
                httpSession.setAttribute( "service", service );
                httpSession.setAttribute( "ensemblePage", ensemblePage );
                // listePage servira a afficher les numéros de pages
                request.setAttribute( "listePage", ensemblePage.getPages().keySet() );
                request.setAttribute( "listeOffres", listeOffres );

                maVue = VUES + "offres.jsp";
            } else if ( action.equals( "offreDuMoment" ) ) {

                Long idService = Long.parseLong( idServiceString );

                TousServicesDAO tousServicesDAO = new TousServicesDAO();

                Service service = tousServicesDAO.findService( idService );

                Offre offre = service.getMeilleureOffre();

                Double prixOffre = 0.00;

                if ( tousServicesDAO.estUnHotel( service.getId() ) )
                    prixOffre = offre.calculDuPrixFinal( "Hotel" );
                else if ( tousServicesDAO.estUnRestaurant( service.getId() ) )
                    prixOffre = offre.calculDuPrixFinal( "Restaurant" );
                else if ( tousServicesDAO.estUneActivite( service.getId() ) )
                    prixOffre = offre.calculDuPrixFinal( "Activite" );

                request.setAttribute( "prixOffre", prixOffre );
                request.setAttribute( "offre", offre );

                maVue = VUES + "detailOffre.jsp";

            }

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
