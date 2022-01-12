package utils;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * classe qui contient la methode prepareSql qui permettra de preparer des
 * requetes qui servent a rechercher des services selon differents criteres
 * 
 */

public class SqlUtils {

    /**
     * methode qui prepare et renvoi une requete pour chercher des services
     * selon differents criteres. Au niveau des parametres , table est le nom de
     * la table ou on fera la recherche , attributs est le nom des attributs en
     * HQL,selections est le nom de l'attribut à remplacer. Exemple si
     * l'attribut est la ville du service en HQL ce sera adresse.ville et pour
     * symboliser que cet attribut doit etre remplacé on notera :ville. valeurs
     * est la valeur par laquelle il faudra remplacé l'attribut exemple Paris
     * pour une ville
     * 
     */

    public static Query prepareSQL( String table, List<String> attributs,
            List<String> selections,
            List<Object> valeurs,
            Session session ) {

        try {
            Query q = null;

            String tuple = minuscule( table );

            String requete = "from " + table + " as " + tuple;

            // si les arguments de selection sont null alors c'est une requete
            // ou on demande toute la table , pas de selection, elle n'aura donc
            // pas de preparation. attributs , selections et valeurs auron
            // toujours la meme taille donc je me contente de tester la taille
            // de valeurs seulement
            if ( attributs != null && selections != null && valeurs.size() > 0 ) {
                requete = requete + "  where ";

                int compteur = 0;
                // si le tableaux des attributs et valeurs est null on ne fait
                // pas cette boucle
                for ( int i = 0; i < attributs.size(); i++ ) {

                    if ( compteur != 0 )
                        requete = requete + " and ";

                    requete = requete + " " + tuple + "." + attributs.get( i ) + "= " + ":" + selections.get( i );
                    compteur++;
                }

                q = session.createQuery( requete );

                for ( int i = 0; i < valeurs.size(); i++ ) {

                    q.setParameter( selections.get( i ), valeurs.get( i ) );

                }
            } else {
                q = session.createQuery( requete );

            }

            return q;

        } catch ( Exception e ) {
            throw new RuntimeException( "probeme technique, service momentantément interrompu" );

        }

    }

    public static String minuscule( String s ) {

        return Character.toLowerCase( s.charAt( 0 ) ) + s.substring( 1, s.length() );
    }

}
