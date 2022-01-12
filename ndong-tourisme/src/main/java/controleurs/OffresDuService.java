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

import modeles.Offre;
import modelesWeb.EnsemblePage;

/**
 * Servlet implementation class OffresDuService. Apres etre passé par le
 * controleur Offres, si on on aura une page d'offres d'une entité de Service
 * avec des choix de numéro pages en lien en bas de page. ce numero est un
 * parametre qui permet de naviguer dans un objet EnsemblePage<Offre> chargé en
 * session
 */
@WebServlet( "/OffresDuService" )
public class OffresDuService extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String            VUES             = "/vues/";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OffresDuService() {

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String maVue = VUES;

        try {

            // il faudra d'abord trouver l'index de la page des resutat a
            // afficher
            int indexPage;

            HttpSession httpSession = request.getSession();

            indexPage = Integer.parseInt( request.getParameter( "indexPage" ) );

            // on recupere l'objet Page
            EnsemblePage<Offre> ensemblePage = (EnsemblePage<Offre>) httpSession.getAttribute( "ensemblePage" );

            // on recupere la page de l'indice indiqué
            List<Offre> listeOffres = ensemblePage.getPage( indexPage );

            httpSession.setAttribute( "ensemblePage", ensemblePage );
            // listePage servira a afficher les numéros de pages
            request.setAttribute( "listePage", ensemblePage.getPages().keySet() );
            request.setAttribute( "listeOffres", listeOffres );

            maVue = VUES + "offres.jsp";

        } catch ( Exception e ) {
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
