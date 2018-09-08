package com.ideas2it.application.service.impl;

import java.util.List;

import com.ideas2it.application.dao.UserDao;
import com.ideas2it.application.dao.impl.UserDaoImpl;
import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.model.User;
import com.ideas2it.application.service.UserService;

/**
 * UserService
 * <p>
 *  Get user details and perform some business logics like add ,delete ,update
 *  etc... with concern users detail and access users data from data access layer.
 * <p>
 * @author Navaneeth 
 */
public class UserServiceImpl implements UserService {   
    private UserDao userDao = new UserDaoImpl();
    
    /**
     * {@inheritDoc} 
     */
    public String addUser(User user) throws ApplicationException {
        return userDao.storeUser(user);             
    }

    /**
     * {@inheritDoc} 
     */ 
    public  List <User> fetchUsers() throws ApplicationException {
        return userDao.retrieveUsers();
    }
}
