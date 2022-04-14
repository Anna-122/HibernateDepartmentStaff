package edu.goncharova.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configure = new Configuration().configure();
                sessionFactory = configure.buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Exception with connect to DB " + e);
            }
        }
        return sessionFactory;
    }

}