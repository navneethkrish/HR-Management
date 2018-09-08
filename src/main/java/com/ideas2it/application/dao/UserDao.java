package com.ideas2it.application.dao;

import java.util.List;

import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.model.User;

/**
 * UserDao
 * <p>
 *  It act as blueprint of implementing class and  which shows some fuctionality 
 *  of that class. 
 * <p>
 * @author Navaneeth 
 */
public interface UserDao {
    
    /**
     * <p>
     *  Used to store a user details like password and username  
     * </p>
     *
     * @param    user       user which have 
     *                      a project details.
     * @return   String     Returns user Email Id.
     */ 
    public String storeUser(User user) throws ApplicationException ;
  
    /**
     * <p>
     *  Used to retrives all user details  
     * </p>
     *
     * @return  users    Retrives the all user Details.
     */   
    public List<User> retrieveUsers() throws ApplicationException ;

}
