package utils;

import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class SqlUtils {

    public static Query prepareSQL( String table, List<Class<?>> tabClass, List<String> attributs,
            List<String> selections,
            List<Object> valeurs,
            Session session ) {

        try {
            Query q = null;

            String tuple = minuscule( table );

            String requete = "from " + table + " as " + tuple;

            // si les arguments de selection sont null alors c'est une requete
            // ou on demande toute la table , pas de selection, elle n'aura donc
            // pas de preparation
            if ( attributs != null && selections != null && valeurs != null ) {
                requete = requete + "  where ";

                int compteur = 0;
                for ( int i = 0; i < attributs.size(); i++ ) {

                    if ( compteur != 0 )
                        requete = requete + " and ";

                    requete = requete + " " + tuple + "." + attributs.get( i ) + "= " + ":" + selections.get( i );
                }

                q = session.createQuery( requete );

                for ( int i = 0; i < valeurs.size(); i++ ) {
                    Method method = getMethode( tabClass.get( i ).getName() );
                    method.invoke( q,
                            selections.get( i ), valeurs.get( i ) );

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

    public static Method getMethode( String s ) {

        try {

            Class<?> query = Class.forName( "org.hibernate.query.Query" );
            Class<?> parametre;
            String param = s.substring( s.lastIndexOf( '.' ) + 1, s.length() );

            parametre = Class.forName( s );

            return query.getMethod( "set" + param, String.class, parametre );

        } catch ( Exception e ) {
            throw new RuntimeException( "probeme technique, service momentantément interrompu" );
        }

    }

}
