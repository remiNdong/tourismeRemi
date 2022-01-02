package controleurs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Acceuil
 */
@WebServlet( urlPatterns = { "/Acceuil", "/acceuil" } )
public class Acceuil extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String            VUES             = "/vues/";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Acceuil() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String maVue = "";
        try {
            maVue = "/index.jsp";

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
