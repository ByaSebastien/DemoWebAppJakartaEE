package be.bstorm.demowebappjakartaee.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryProvider {

    // Instance unique de l'EntityManagerFactory
    private static EntityManagerFactory entityManagerFactory;

    // Nom de l'unité de persistance (doit correspondre à celui dans persistence.xml)
    private static final String PERSISTENCE_UNIT_NAME = "DemoJakarta";

    // Méthode pour obtenir l'EntityManagerFactory
    public static synchronized EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            // Créer l'EntityManagerFactory s'il n'existe pas encore
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory;
    }

    // Méthode pour fermer l'EntityManagerFactory
    public static synchronized void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
