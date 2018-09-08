package com.ideas2it.application.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.application.commons.constants.Constants;    
import com.ideas2it.application.dao.UserDao;
import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.hibernetUtils.SessionFactoryUtil;
import com.ideas2it.application.logger.ApplicationLogger;
import com.ideas2it.application.model.User;

/**
 * UserDaoImpl
 * <p>
 *  It act as database of user and used to manipulate user login details
 *  from users Login details. 
 * </p>
 * @author Navanith.
 */
public class UserDaoImpl implements UserDao {

    private static final String ADD_ERROR_INFO = "ERROR WHILE ADDING USERNAME:"; 
    private static final String DISPLAY_ERROR_INFO ="ERROR WHILE RETRIEVE USERS";
    private static final String STATUS ="status";
   
    /**
     * {@inheritDoc} 
     */
    public String storeUser(User user) throws ApplicationException {
        String userId = null;
        Session session = null;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Transaction transcation = session.beginTransaction();
		    session.save(user);
            userId = user.getEmailId();
            transcation.commit();
        } catch (HibernateException e) {
            ApplicationLogger.error(ADD_ERROR_INFO+user.getEmailId(), e);
            throw new ApplicationException(ADD_ERROR_INFO+user.getEmailId());
        } finally {
            if(null != session ) {
                session.close();
            } 
        } 
        return userId; 
    }

    /**
     * {@inheritDoc} 
     */
    public List<User> retrieveUsers() throws ApplicationException {
        Session session = null;
        List listOfUsers = null;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Criteria criteria = session.createCriteria(User.class);
            listOfUsers = criteria.list(); 
            return listOfUsers;
        } catch (HibernateException e) {
            ApplicationLogger.error(DISPLAY_ERROR_INFO, e);
            throw new ApplicationException(DISPLAY_ERROR_INFO); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }    
	}
}
