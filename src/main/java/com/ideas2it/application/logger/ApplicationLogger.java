package com.ideas2it.application.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * <p>
 *  Used to log the whole Application for debugging purpose.
 * <p>  
 */
public class ApplicationLogger {

    private static final Logger logger; 
    
    static {
        logger = Logger.getLogger(ApplicationLogger.class); 
        DOMConfigurator.configure("logConfig.xml");
    }
    
    public static void debug(String message ,Exception exception) {
        logger.debug(message, exception);
    }
    
    public static void info(String message) {
        logger.info(message);
    }
    
    public static void warn(String message) {
        logger.warn(message);
    }
    
    public static void error(String message ,Exception exception) {
        logger.error(message ,exception);
    }
    
    public static void fatal(String message) {
        logger.fatal(message);
    }
}
