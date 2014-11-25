package com.tsystems.Util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    //private static  EntityManagerFactory emf;
    //private static EntityManager em;
    private static Session session;
    private static SessionFactory sessionFactory;

    public static void startTransaction() {
        session.getTransaction().begin();
    }

    public static void commitTransaction() {
        session.getTransaction().commit();
    }

    public static void rollbackTransaction() {
        session.getTransaction().rollback();
    }

    private HibernateUtil() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static Session getSession() {
        if (session == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
        }
        return session;
    }

    public static SessionFactory getSessionFactory() {
        if (session == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
        }
        return sessionFactory;
    }

}
