package compulsory.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class EntityManagerFactorySingleton {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ExamplePU");

    private EntityManagerFactorySingleton() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
