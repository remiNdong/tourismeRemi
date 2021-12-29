package controleurs;

import java.io.IOException;
import java.util.Arrays;
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
            TousServicesDAO tousServicesDAO = new TousServicesDAO();

            String action = request.getParameter( "action" );

            // lors de l'arrivee au routeur on sera toujours a la page 1 a
            // afficher
            int indexPage = 1;

            HttpSession httpSession = request.getSession();

            if ( action.equals( "Tous Les Services" ) ) {

                String ville = request.getParameter( "ville" );
                // parametre de la fonction de preparations de requete Sql
                // pour la selection de Service on a un seul critere de
                // selection qui est la ville
                List<Object> valeurs = null;

                if ( ville != null && !ville.equals( "" ) )
                    valeurs = Arrays.asList( ville );

                List<Service> listToutServices = tousServicesDAO.selectionServices( valeurs );

                EnsemblePage<Service> ensemblePage = new EnsemblePage<Service>( listToutServices );

                // chaque ensemble a au moins une page
                // par contre si c'etait la derniere page il faudra le notifier
                if ( ensemblePage.getPages().keySet().size() > 1 )
                    request.setAttribute( "listeFini", "non" );
                else
                    request.setAttribute( "listeFini", "oui" );

                // on recupere la page de l'indice indiqué
                // il y en a au moins une
                List<Service> listServices = ensemblePage.getPage( indexPage );

                // on recupere la liste des villes a afficher dans le select de
                // la jsp
                List<String> listVilles = tousServicesDAO.listVilles();
                request.setAttribute( "villes", listVilles );

                httpSession.setAttribute( "ensemblePage", ensemblePage );
                request.setAttribute( "listServices", listServices );
                // on prepare l'index de la futur page au cas ou il y a
                // pagination
                httpSession.setAttribute( "indexPage", indexPage + 1 );

                maVue = VUES + "tousServices.jsp";

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
