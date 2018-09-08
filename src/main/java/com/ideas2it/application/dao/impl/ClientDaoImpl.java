package com.ideas2it.application.dao.impl;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.application.dao.ClientDao;
import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.hibernetUtils.SessionFactoryUtil;
import com.ideas2it.application.logger.ApplicationLogger;
import com.ideas2it.application.model.Client;

/**
 * ClientDaoImpl
 * <p>
 *  It act as database of client and used manipulate client details
 *  from clients details. 
 * </p>
 * @author Navanith.
 */
public class ClientDaoImpl implements ClientDao  {

    private static final String ADD_ERROR_INFO =
                                              "ERROR WHILE ADDING Client NAME:"; 
    private static final String SEARCH_ERROR_INFO =
                                              "ERROR WHILE RETRIEVE Client ID:";
    private static final String DELETE_ERROR_INFO = 
                                              "ERROR WHILE DELETE Client ID:";
    private static final String DISPLAY_ERROR_INFO =
                                              "ERROR WHILE DISPLAY ClientS";
    private static final String UPDATE_ERROR_INFO = 
                                              "ERROR WHILE UPDATE Client ID";
    private static final String ID ="id";
    private static final String STATUS ="status";  
    
    /**
     * {@inheritDoc} 
     */
    public int storeClient(Client client) throws ApplicationException{
        Session session = null;
        int clientId = 0;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Transaction transcation = session.beginTransaction();
		    session.save(client);
            clientId = client.getId();
            transcation.commit();
        } catch (HibernateException e) {
            ApplicationLogger.error(ADD_ERROR_INFO+client.getName(), e);
            throw new ApplicationException(ADD_ERROR_INFO+client.getName()); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }
        return clientId;
    }

    /**
     * {@inheritDoc} 
     */
    public List<Client> retrieveClients() throws ApplicationException {
        Session session = null;
        List listOfClients = null;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Criteria criteria = session.createCriteria(Client.class);
            criteria.add(Restrictions.eq(STATUS,1)); 
            listOfClients = criteria.list();
            for(Object clientIterator :listOfClients) {
                Client client = (Client)clientIterator;
                client.getProjects();
                System.out.println(client.getProjects().size()+"*****************\n\n\n\n\n");
            }
        } catch (HibernateException e) {
            ApplicationLogger.error(DISPLAY_ERROR_INFO, e);
            throw new ApplicationException(DISPLAY_ERROR_INFO); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }    
	    return listOfClients; 
    }
    
    /**
     * {@inheritDoc} 
     */
    public boolean deleteClient(Client client) throws ApplicationException {
        Session session = null;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Transaction transcation = session.beginTransaction();
            client.setStatus(0);   
		    session.update(client);
            transcation.commit(); 
            return Boolean.TRUE;
        } catch (HibernateException e) {
            ApplicationLogger.error(DELETE_ERROR_INFO+client.getId(), e);
            throw new ApplicationException(DELETE_ERROR_INFO+client.getId()); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }   
    }
    
    /**
     * {@inheritDoc} 
     */
    public Client searchClient(int clientId) throws ApplicationException {
        Session session = null;
        Client client = null; 
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Criteria criteria = session.createCriteria(Client.class);
            criteria.add(Restrictions.eq(ID, clientId));
            criteria.add(Restrictions.eq(STATUS,1));
            client = (Client) criteria.uniqueResult();
        } catch (HibernateException e) {
            ApplicationLogger.error(SEARCH_ERROR_INFO+clientId, e);
            throw new ApplicationException(SEARCH_ERROR_INFO+clientId); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }   
        return client; 
    }
   
    /**
     * {@inheritDoc} 
     */
    public boolean updateClient(Client client) throws ApplicationException {
        Session session = null;
        try {
             session = SessionFactoryUtil.getInstance().getSession();
             Transaction transcation = session.beginTransaction();
             client.setStatus(1);
		     session.update(client);
             transcation.commit(); 
             return Boolean.TRUE;
        } catch (HibernateException e) {
            ApplicationLogger.error(UPDATE_ERROR_INFO+client.getId(), e);
            throw new ApplicationException(UPDATE_ERROR_INFO+client.getId()); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }    
    }
     
    /**
     * {@inheritDoc} 
     */
    public Client searchDeletedClient(int clientId) throws ApplicationException {
        Session session = null;
        Client client = null; 
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Criteria criteria = session.createCriteria(Client.class);
            criteria.add(Restrictions.eq(ID, clientId));
            criteria.add(Restrictions.eq(STATUS,0));
            client = (Client) criteria.uniqueResult();
            client.getProjects(); 
        } catch (HibernateException e) {
            ApplicationLogger.error(SEARCH_ERROR_INFO+clientId, e);
            throw new ApplicationException(SEARCH_ERROR_INFO+clientId); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }   
        return client; 
    }
  
    /**
     * {@inheritDoc} 
     */
    public List<Client> fetchDeletedClients() throws ApplicationException {
        Session session = null;
        List clients = null;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Criteria criteria = session.createCriteria(Client.class);
            criteria.add(Restrictions.eq(STATUS,0)); 
            clients = criteria.list();
            for(Object clientIterator : clients) {
                Client client = (Client)clientIterator;
                client.getProjects();
            }
        } catch (HibernateException e) {
            ApplicationLogger.error(DISPLAY_ERROR_INFO, e);
            throw new ApplicationException(DISPLAY_ERROR_INFO); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }    
	    return clients; 
    }
}

