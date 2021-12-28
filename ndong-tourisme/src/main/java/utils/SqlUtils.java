package utils;

import java.lang.reflect.Method;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class SqlUtils {

    public static Query prepareSQL( String table, Class<?>[] tabClass, String[] attributs, String[] selections,
            Object[] valeurs,
            Session session ) {

        try {

            String tuple = minuscule( table );

            String requete = "from " + table + " as " + tuple + "  where ";

            int compteur = 0;
            for ( int i = 0; i < attributs.length; i++ ) {

                if ( compteur != 0 )
                    requete = requete + " and ";

                requete = requete + " " + tuple + "." + attributs[i] + "= " + ":" + selections[i];
            }
            System.out.println( requete );

            Query q = session.createQuery( requete );

            for ( int i = 0; i < valeurs.length; i++ ) {
                Method method = getMethode( tabClass[i].getName() );
                method.invoke( q,
                        selections[i], valeurs[i] );

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
