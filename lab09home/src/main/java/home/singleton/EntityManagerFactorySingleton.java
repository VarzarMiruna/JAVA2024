package home.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class EntityManagerFactorySingleton {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("HomePU");

    private EntityManagerFactorySingleton() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
