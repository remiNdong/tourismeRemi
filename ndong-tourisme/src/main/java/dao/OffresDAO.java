package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import configuration.ConfigurationBDD;
import modeles.Offre;

public class OffresDAO {

    /**
     * Objet Session de Hibernate
     */
    private Session session;

    public OffresDAO() {

        try {

            session = ConfigurationBDD.getSession();

        } catch ( Exception e ) {
            throw new RuntimeException( "problème de connexion à la base de données" );
        }
    }

    public List<Offre> listeOffres() {

        Query q = session.createQuery( "from Offre " );

        List<Offre> offres = q.list();
        if ( offres.size() == 0 )
            throw new RuntimeException( "Aucune Offre trouvée" );

        return offres;
    }

    public Offre findOffre( Long idOffre ) {

        Offre offre = session.get( Offre.class, idOffre );

        if ( offre == null )
            throw new RuntimeException( "L'identifiant " + idOffre + " ne correspond à aucune Offre " );

        return offre;

    }

}
