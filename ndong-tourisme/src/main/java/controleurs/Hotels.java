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

import dao.HotelsDAO;
import modeles.Hotel;
import modelesWeb.EnsemblePage;

/**
 * Servlet implementation class Hotels.Apres etre passé par le Routeur, si on
 * selectionne les Hotels on aura une page d'hotels avec des choix de numéro
 * pages en lien en bas de page. ce numero est un parametre qui permet de
 * naviguer dans un objet EnsemblePage<Hotel> chargé en session
 */
@WebServlet( "/Hotels" )
public class Hotels extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String            VUES             = "/vues/";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hotels() {

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String maVue = VUES;

        try {

            HotelsDAO hotelsDAO = new HotelsDAO();

            // il faudra d'abord trouver l'index de la page des resutat a
            // afficher
            int indexPage;

            HttpSession httpSession = request.getSession();

            indexPage = Integer.parseInt( request.getParameter( "indexPage" ) );

            // on recupere l'objet Page
            EnsemblePage<Hotel> ensemblePage = (EnsemblePage<Hotel>) httpSession.getAttribute( "ensemblePage" );

            // on recupere la page de l'indice indiqué
            List<Hotel> listHotels = ensemblePage.getPage( indexPage );

            httpSession.setAttribute( "ensemblePage", ensemblePage );
            // listePage servira a afficher les numéros de pages
            request.setAttribute( "listePage", ensemblePage.getPages().keySet() );
            request.setAttribute( "listHotels", listHotels );

            // on recupere la liste des villes a afficher dans le select de
            // la jsp
            List<String> listVilles = hotelsDAO.listVilles();
            request.setAttribute( "villes", listVilles );

            // on recupere la liste des etoiles a afficher dans le select de
            // la jsp
            List<Integer> listEtoiles = hotelsDAO.listEtoiles();
            request.setAttribute( "etoiles", listEtoiles );

            maVue = VUES + "hotels.jsp";

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
