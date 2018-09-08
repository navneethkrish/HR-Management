package com.ideas2it.application.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.ideas2it.application.commons.constants.Constants;
import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.logger.ApplicationLogger;
import com.ideas2it.application.model.User;
import com.ideas2it.application.service.impl.UserServiceImpl;
import com.ideas2it.application.service.UserService;
import com.ideas2it.application.utils.dateutils.DateUtils;
import com.ideas2it.application.utils.stringutils.StringUtils;

/**
 * UserController
 * 
 * <p>
 *  Represents a login of application and
 *  All User login details are get from user and managed in this class.  
 * <P>
 *
 * @author Navaneeth 
 */ 
public class UserController extends HttpServlet {  
    private UserService userService   = new UserServiceImpl();
    private static final long serialVersionUID = 1L;
    
    public void init() throws ServletException {
      
    }
    
    /**
     * <p>
     *   Used to get a user login detail like emailId, password , privileges..
     * </p>
     * 
     * @param     request       Request which is send by user/client.
     *
     * @param     response      Response which is respond by server. 
     * 
     * @return    UserLogin     userLogin which have user login details.
     */
    private User getUserLogin(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {
        User user = new User(); 
        user.setEmailId(request.getParameter(Constants.EMAILID));
        user.setPassword(request.getParameter(Constants.PASSWORD));
        return user;      
    }
    
    /**
     * <p>
     *   Used to get a user login detail like emailId, password , privileges..
     * </p>
     * 
     * @param     request       Request which is send by user/client.
     *
     * @param     response      Response which is response of server. 
     */
    private void displayUsers(HttpServletRequest request,
	       HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher
                      (Constants.DISPLAY_USER_PAGE ).forward(request, response);
        
    }
    
    /**
     * <p>
     *   Used to directly interact with browser with request and send response back to 
     *   client.
     * </p>
     * 
     * @param     request       Request which is send by user/client.
     *
     * @param     response      Response which is send by server. 
     */
    protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        String userId = null; 
        RequestDispatcher requestDispatcher = null;  
        if(request.getParameter(Constants.OPERATION).equals(Constants.SIGNUP)) {
            try {
                userId = userService.addUser
                                         (this.getUserLogin(request ,response));
                if(null != userId){
                    request.setAttribute(Constants.Id , 1);
                    request.getRequestDispatcher
                                   (Constants.LOGIN).forward(request, response);
                } else {
                    request.setAttribute(Constants.Id , 0); 
                    request.getRequestDispatcher
                                   (Constants.LOGIN).forward(request, response);
                }  
            } catch (ApplicationException e) {
                request.setAttribute(Constants.Id , -1); 
                request.getRequestDispatcher
                                   (Constants.LOGIN).forward(request, response);
            } 
        } else if(request.getParameter(Constants.OPERATION).equals
                                                          (Constants.DISPLAY)) {  
            this.displayUsers(request ,response); 
        } else if(request.getParameter(Constants.OPERATION).
                                                  equals(Constants.USERLOGIN)) {
            try {  
                int flag = 0;
                for(User userIndex : userService.fetchUsers()) {
                    if (userIndex.getEmailId().equals
                                    (request.getParameter(Constants.EMAILID))&&
                         userIndex.getPassword().
                             equals(request.getParameter(Constants.PASSWORD))) {
                         flag = 1; 
                    }  
                }   
                if(flag == 1) {
                    HttpSession session = request.getSession(false);
			        session.setAttribute(Constants.USERUL ,
                                       request.getParameter(Constants.EMAILID));
			        session.setMaxInactiveInterval(5*60);
			        request.getRequestDispatcher(Constants.LOGIN_SUCCESS).
                                                     forward(request, response);
                } else {
                    request.setAttribute(Constants.Id , 0);
                    request.getRequestDispatcher
                                   (Constants.LOGIN).forward(request, response);
                }
            }  catch (ApplicationException e) {
                request.setAttribute(Constants.Id , -1); 
                request.getRequestDispatcher(Constants.LOGIN).
                                                     forward(request, response);
            }   
        } else if(request.getParameter(Constants.OPERATION).
                                                     equals(Constants.LOGOUT)) {
             HttpSession session = request.getSession(false); 
             if(null != session) {
                 session.invalidate();
                 request.getRequestDispatcher(Constants.LOGIN).
                                                     forward(request, response);
                 System.out.println(session.getAttribute(Constants.USERUL)); 
             }      
       }
    } 
    
    
    /**
     * <p>
     *   Used to directly interact with browser with request and send response back to 
     *   client.
     * </p>
     * 
     * @param     request       Request which is send by user/client.
     *
     * @param     response      Response which is send by server. 
     */
    protected void doGet(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (null != session && null != session.getAttribute(Constants.USER)) {
            request.getRequestDispatcher(Constants.EMPLOYEE_INDEX).
                                                     forward(request, response);
        } else {
            request.getRequestDispatcher(Constants.LOGIN).
                                                     forward(request, response);
        }  
    }
}
