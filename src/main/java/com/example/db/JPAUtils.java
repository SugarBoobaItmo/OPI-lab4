package com.example.db;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Properties;

@ApplicationScoped
public class JPAUtils {
    private final EntityManagerFactory factory;

    public JPAUtils() {
        try {
            Properties info = new Properties();
            info.load(JPAUtils.class.getClassLoader().getResourceAsStream("/db.cfg"));
            factory = Persistence.createEntityManagerFactory("default", info);
        } catch (Throwable ex) {
            System.err.println("Something went wrong during initializing DB: " + ex);
            throw new ExceptionInInitializerError();
        }
    }

    @Produces
    public EntityManager getEntityManagerFactory() {
        return factory.createEntityManager();
    }
}
