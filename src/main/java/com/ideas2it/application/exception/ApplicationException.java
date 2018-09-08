package com.ideas2it.application.exception;

/**
 * ApplicationException 
 * <p>
 *  It is user defined custom Exception class which is used to give valid and   
 *  understandable message to user.
 * <p>
 * @author Navanith
 */
public class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }  
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
