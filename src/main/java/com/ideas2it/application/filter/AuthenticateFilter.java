package com.ideas2it.application.filter;

import java.io.IOException;

import javax.servlet.annotation.WebFilter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import com.ideas2it.application.commons.constants.Constants;

/** 
 * <p>
 *  It is the class invoked every time by web container when it request send to 
 *  server and response send abck to client.  FilterChain is used to invoke the next
 *  request in the filter chain
 * </p>
 */

public class AuthenticateFilter implements Filter {
  
    public void init(FilterConfig filterConfig) {

    } 
    
    /**
     * <p>
     * It is the method invoked every time by web container when it request send to 
     * server and response send abck to client  FilterChain is used to invoke the next
     *  request in the filter chain
     * </p>
     * @param         request     It is request send by the client
     * @param         response    It is the response send by the server to
     *                            Client 
     * @param         chain       It is fiter chain which is used to call 
     *                            next request.  
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
                                            throws ServletException, IOException {    
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(Boolean.FALSE);
        boolean loggedFirst = null != session && 
                                   null == session.getAttribute(Constants.USER);
        if (loggedFirst&&request.getRequestURI().equals(Constants.LOGIN_URL)) {
            chain.doFilter(request, response); 
        } else if (null != session && null != session.getAttribute(Constants.USER)) {
            if(request.getRequestURI().equals(Constants.WEB_URL)) { 
                System.out.println(); 
                request.getRequestDispatcher(Constants.EMPLOYEE_JSP).
                                                     forward(request, response);  
            } else if(!request.getRequestURI().equals(Constants.URLONE)) {
                if (request.getRequestURI().equals(Constants.URLTWO)) {
                   request.getRequestDispatcher(Constants.EMPLOYEE_JSP).
                                                     forward(request, response);
                } else if(request.getRequestURI().
                                               equals(Constants.URLTHREE)) {
                   request.getRequestDispatcher(Constants.CLIENT_JSP).
                                                     forward(request, response);
                } else if(request.getRequestURI().
                                              equals(Constants.URLFOUR)) {
                   request.getRequestDispatcher(Constants.PROJECT_JSP).
                                                     forward(request, response);
                }   
                chain.doFilter(request, response); 
            } else {
                 request.getRequestDispatcher
                             (Constants.EMPLOYEE_JSP).forward(request, response);  
            }
        } else {
           request.getRequestDispatcher
                             (Constants.LOGIN_JSP).forward(request, response);  
        } 
       
    }
   
    public void destroy() {

    } 
}
