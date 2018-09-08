package com.ideas2it.application.hibernetUtils;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.logger.ApplicationLogger;

/**
 * SessionFactoryUtil
 * <p>
 *  It represents factory of session which is a create session and connecting to
 *  Database with help of mapping xml.
 * </p>
 */ 
public class SessionFactoryUtil {
    private static SessionFactoryUtil instance = null; 
    private static SessionFactory sessionFactory = null;
    private static String ERROR_INFO = "ERROR_CONNECTING...";
    private static String CONFIG = "hibernate.cfg.xml"; 

    private SessionFactoryUtil() throws ApplicationException {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(CONFIG);
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) { 
            ApplicationLogger.error(ERROR_INFO, e);
            throw new ApplicationException(ERROR_INFO);  
        }
    }
    
    /**
     * <p>
     *   Used to return SessionFactory which is a Factory for creating session.
     * </p>
     *
     * @return   sessionFactory   Returns sessionFactory which is a factory for 
     *                            creating session. 
     */ 
    public static SessionFactoryUtil getInstance() throws ApplicationException {
        if(instance == null){
            instance  = new SessionFactoryUtil();
        }
        return instance;
    } 
    
    /**
     * <p>
     *   Used to return session which is used to connect dataBase.
     * </p>
     *
     * @return   session   Returns session to perfrom CRUD operation. 
     */   
    public Session getSession()  {
        return sessionFactory.openSession();
    }
}
