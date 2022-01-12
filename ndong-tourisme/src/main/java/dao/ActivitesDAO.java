package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import configuration.ConfigurationBDD;
import modeles.Activite;
import utils.SqlUtils;

/**
 * Objet qui permet de faire les transactions concernant les Activites avec la
 * base de données
 */
public class ActivitesDAO {

    /**
     * Objet Session de Hibernate
     */
    private Session session;

    public ActivitesDAO() {

        try {

            session = ConfigurationBDD.getSession();

        } catch ( Exception e ) {
            throw new RuntimeException( "problème de connexion à la base de données" );
        }
    }

    /**
     * Methode qui sera utilisé pour selectionner une activité selon la ville et
     * le type
     */
    public List<Activite> selectionActivites( List<String> attributs, List<String> selections,
            List<Object> valeurs ) {

        String table = "Activite";

        Query q = SqlUtils.prepareSQL( table, attributs,
                selections, valeurs, session );

        List<Activite> activites = q.list();
        if ( activites.size() == 0 )
            throw new RuntimeException( "Aucune Activité trouvée" );

        return activites;

    }

    public List<Activite> listeActivites() {

        Query q = session.createQuery( "from Activite " );

        List<Activite> activites = q.list();
        if ( activites.size() == 0 )
            throw new RuntimeException( "Aucune Activité trouvée" );

        return activites;
    }

    public Activite findActivite( Long idActivite ) {

        Activite activite = session.get( Activite.class, idActivite );

        if ( activite == null )
            throw new RuntimeException( "L'identifiant " + idActivite + " ne correspond à aucune Activité " );

        return activite;

    }

    public List<String> listVilles() {

        Query q = session.createQuery( "select distinct activite.adresse.ville from Activite as activite" );

        List<String> listVilles = q.list();
        if ( listVilles.size() == 0 )
            throw new RuntimeException( "Aucune activité trouvée" );

        return listVilles;

    }

    public List<String> listTypes() {

        Query q = session.createQuery( "select distinct activite.type from Activite as activite" );

        List<String> listTypes = q.list();
        if ( listTypes.size() == 0 )
            throw new RuntimeException( "Aucune activité trouvée" );

        return listTypes;

    }

}
