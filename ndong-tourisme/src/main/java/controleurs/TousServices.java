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

            // il faudra d'abord trouver l'index de la page des resutat a
            // afficher
            int indexPage;

            HttpSession httpSession = request.getSession();

            indexPage = Integer.parseInt( request.getParameter( "indexPage" ) );

            // on recupere l'objet Page
            EnsemblePage<Service> ensemblePage = (EnsemblePage<Service>) httpSession.getAttribute( "ensemblePage" );

            // on recupere la page de l'indice indiqué
            List<Service> listServices = ensemblePage.getPage( indexPage );

            httpSession.setAttribute( "ensemblePage", ensemblePage );
            request.setAttribute( "listePage", ensemblePage.getPages().keySet() );
            request.setAttribute( "listServices", listServices );

            // on recupere la liste des villes a afficher dans le select de
            // la jsp
            List<String> listVilles = tousServicesDAO.listVilles();
            request.setAttribute( "villes", listVilles );

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
