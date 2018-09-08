package com.ideas2it.application.service;

import java.util.List;

import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.model.User;

/**
 * UserService
 * <p>
 *  It act as blueprint of implementing class and  which shows some fuctionality 
 *  of that class. 
 * <p>
 * @author Navaneeth 
 */
public interface UserService {   
    
    /**
     * <p>
     *  Used to get user login details from 
     *  the user and added to the users details. 
     * </p>
     *
     * @param   user           user that is used to store 
     *                         a userLogin details.
     * @return  userId         Adds user and returns user unique Id. 
     */
    public String addUser(User user) throws ApplicationException;
  

    /**
     * <p>
     *  Used to fetch all userLogin details
     * </p>
     *
     * @return  users   Retrives all the userLogin details. 
     */
    public  List <User> fetchUsers() throws ApplicationException;
}
