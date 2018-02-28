package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;

/**
 * @author Karel Maesen, Geovise BVBA
 *         creation-date: 6/19/12
 */
public class JPAUtil {

    private static final EntityManagerFactory emFactory;

    static {
        try {

//            URL resource = Thread.currentThread().getContextClassLoader().getResource( "META-INF/persistence.xml" );
//            if (resource == null) throw new RuntimeException("No pers file found");
            emFactory = Persistence.createEntityManagerFactory("SPATIAL-JPA");
        }catch(Throwable ex){
            System.err.println("Cannot create EntityManagerFactory.");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager createEntityManager() {
        return emFactory.createEntityManager();
    }

    public static void close(){
        emFactory.close();
    }
}
