package com.ideas2it.application.utils.stringutils;

import java.util.regex.Pattern;

import com.ideas2it.application.commons.constants.Constants;

/**
 * <p>
 *  Used to validates input details.  
 * </p>
 */   
public class StringUtils {
   
    /**
     * <p>
     *  Used to validates name or any text.  
     * </p>
     *
     * @param   userInput       name or any text.
     * @return  boolean         Validates and returns boolean value.
     */
    public static boolean isAlphapetic(String userInput) {
        return Pattern.matches(Constants.NAME_VALIDATION, userInput);
    }
    
    /**
     * <p>
     *  Used to validates id or any numeric.  
     * </p>
     * @param   userInput       id or any numeric value.
     * @return  boolean         Validates and returns boolean value.
     */
    public static boolean isNumeric(String userInput) {
        return Pattern.matches(Constants.ID_VALIDATION, userInput);
    }
 
    /**
     * <p>
     *  Used to validates phone No.  
     * </p>
     * @param   userInput       phone no.
     * @return  boolean         Validates and returns boolean value.
     */
    public static boolean isValidPhoneNo(String userInput) {
        return Pattern.matches(Constants.PHONENO_VALIDATION, userInput);
    }
 
    /**
     * <p>
     *  Used to validates users email id.  
     * </p>
     * @param   userInput       users email id.
     * @return  boolean         Validates and returns boolean value.
     */
    public static boolean isValidEmailId(String userInput) {
        return Pattern.matches(Constants.EMAIL_VALIDATION, userInput);
    }

    /**
     * <p>
     *  Used to validates Date.  
     * </p>
     * @param   userInput       date.
     * @return  boolean         Validates and returns boolean value.
     */
    public static boolean isValidDOB(String userInput) {
           
        return Pattern.matches(Constants.DATE_VALIDATION, userInput);
    }
    
    /**
     * <p>
     *  Used to validates salary.  
     * </p>
     * @param  userInput       salary.
     * @return boolean         Validates and returns boolean value.
     */
    public static boolean isValidSalary(String userInput) {
        return Pattern.matches(Constants.SALARY_VALIDATION, userInput);
    } 
    
}

