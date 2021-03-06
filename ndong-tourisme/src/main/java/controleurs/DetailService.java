package controleurs;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TousServicesDAO;
import modeles.Activite;
import modeles.Hotel;
import modeles.Internaute;
import modeles.Notation;
import modeles.Restaurant;
import modeles.Service;

/**
 * Servlet implementation class DetailService. Celle ci permettra de voir le
 * detail d'un Service et de noter ce Service. Il y a une seule Servlet
 * DetailService pour tous les Services mais des criteres seront affichés ou non
 * selon le type de Service (Hotel, Restaurant ou Activité)
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

        String idServiceString = request.getParameter( "idService" );

        try {
            Long idService = Long.parseLong( idServiceString );

            TousServicesDAO tousServicesDAO = new TousServicesDAO();

            // il faudra indiquer dans la requete le type de Service car
            // certains attributs seront a afficher ou non dans la jsp selon le
            // type de service

            if ( tousServicesDAO.estUnHotel( idService ) ) {
                Hotel service = tousServicesDAO.findHotel( idService );
                request.setAttribute( "service", service );
                request.setAttribute( "typeService", "hotel" );

            } else if ( tousServicesDAO.estUnRestaurant( idService ) ) {
                Restaurant service = tousServicesDAO.findRestaurant( idService );
                request.setAttribute( "service", service );
                request.setAttribute( "typeService", "restaurant" );

            } else if ( tousServicesDAO.estUneActivite( idService ) ) {
                Activite service = tousServicesDAO.findActivite( idService );
                request.setAttribute( "service", service );
                request.setAttribute( "typeService", "activite" );

            }

            String action = request.getParameter( "action" );

            boolean newCommentaire = false;

            if ( action != null ) {
                if ( action.equals( "ajoutCommentaire" ) ) {

                    newCommentaire = true;
                    request.setAttribute( "newCommentaire", newCommentaire );

                    String prenomInternaute = request.getParameter( "prenomInternaute" );
                    String nomInternaute = request.getParameter( "nomInternaute" );
                    String mailInternaute = request.getParameter( "mailInternaute" );

                    Internaute internaute = new Internaute();
                    internaute.setPrenom( prenomInternaute );
                    internaute.setNom( nomInternaute );
                    internaute.setEmail( mailInternaute );

                    Notation notation = new Notation();
                    notation.setInternaute( internaute );
                    Service service = tousServicesDAO.findService( idService );
                    notation.setService( service );

                    // selon le type de service qu'on note on aura des criteres
                    // pris en compte ou non

                    String classement = request.getParameter( "classement" );
                    if ( classement != null ) {
                        notation.setNbEtoiles( Integer.parseInt( classement ) );
                    }

                    String propreteHotel = request.getParameter( "propreteHotel" );
                    if ( propreteHotel != null ) {
                        notation.setPropreteHotel( Integer.parseInt( propreteHotel ) );
                    }
                    String calmeHotel = request.getParameter( "calmeHotel" );
                    if ( calmeHotel != null ) {
                        notation.setCalmeHotel( Integer.parseInt( calmeHotel ) );
                    }

                    String acceuilRestaurant = request.getParameter( "acceuilRestaurant" );
                    if ( acceuilRestaurant != null ) {
                        notation.setAcceuilRestaurant( Integer.parseInt( acceuilRestaurant ) );
                    }

                    String qualiteRestaurant = request.getParameter( "qualiteRestaurant" );
                    if ( qualiteRestaurant != null ) {
                        notation.setQualiteRestaurant( Integer.parseInt( qualiteRestaurant ) );
                    }

                    String sensationsActivite = request.getParameter( "sensationsActivite" );
                    if ( sensationsActivite != null ) {
                        notation.setSensationsActivite( Integer.parseInt( sensationsActivite ) );
                    }

                    String accompagnateursActivite = request.getParameter( "accompagnateursActivite" );
                    if ( accompagnateursActivite != null ) {
                        notation.setQualiteAccompagnateur( Integer.parseInt( accompagnateursActivite ) );
                    }

                    String com = request.getParameter( "com" );
                    if ( com != null ) {
                        notation.setCommentaire( com );
                    }

                    tousServicesDAO.insertNotation( notation );

                    // on recree un DAO car la session a ete fermee apres la
                    // transaction
                    tousServicesDAO = new TousServicesDAO();

                }

            }

            Service serviceNote = tousServicesDAO.findService( idService );
            List<Notation> notationsTriees = serviceNote.getNotationsTriees();
            request.setAttribute( "notationsTriees", notationsTriees );

            maVue = VUES + "detailService.jsp";

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
