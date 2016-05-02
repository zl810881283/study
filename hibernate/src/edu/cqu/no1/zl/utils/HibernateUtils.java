package edu.cqu.no1.zl.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by zl on 2016/4/27.
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
        Configuration cfg = new Configuration().configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
