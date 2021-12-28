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

import dao.TousServicesDAO;
import modeles.Service;
import modelesWeb.EnsemblePage;

/**
 * Servlet implementation class TousServices
 */
@WebServlet( "/TousServices" )
public class TousServices extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String            VUES             = "/vues/";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TousServices() {

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String maVue = VUES;

        try {
            TousServicesDAO tousServicesDAO = new TousServicesDAO();
            List<Service> listToutServices = tousServicesDAO.listeServices();

            // il faudra d'abord trouver l'index de la page des resutat a
            // afficher
            int indexPage;

            HttpSession httpSession = request.getSession();

            indexPage = (Integer) httpSession.getAttribute( "indexPage" );

            // on recupere l'objet Page s'il existe
            EnsemblePage<Service> ensemblePage = (EnsemblePage<Service>) httpSession.getAttribute( "ensemblePage" );

            // sinon on cree cet objet
            if ( ensemblePage == null )
                ensemblePage = new EnsemblePage<Service>( listToutServices );

            // on recupere la page de l'indice indiqué
            List<Service> listServices = ensemblePage.getPage( indexPage );

            // chaque ensemble a au moins une page
            // par contre si c'etait la derniere page il faudra le notifier
            // on recupere la page de l'indice indiqué
            List<Service> listeSuivante = ensemblePage.getPage( indexPage + 1 );

            if ( listeSuivante == null )
                request.setAttribute( "listeFini", "oui" );
            else
                request.setAttribute( "listeFini", "non" );

            httpSession.setAttribute( "ensemblePage", ensemblePage );
            httpSession.setAttribute( "listServices", listServices );
            httpSession.setAttribute( "indexPage", indexPage + 1 );

            maVue = VUES + "tousServices.jsp";

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
