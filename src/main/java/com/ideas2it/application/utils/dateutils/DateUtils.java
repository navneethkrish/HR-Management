package com.ideas2it.application.utils.dateutils;

import java.util.Date;

/**
 * <p>
 *  Used to perfrom date operations like addition of two dates and find 
 *  difference between two dates.
 * </p>
 */
public class DateUtils {
    
   /**
    * <p>
    *  Used to calculate difference between two dates.  
    * </p>
    *
    * @param    Date        fromDate.
    *
    * @return  Difference   Diffrence in years. 
    */
    public static int getDateDifference(Date fromDate) {   
        long timeBetween = new Date().getTime() - fromDate.getTime();
        double yearsBetween = timeBetween / 3.15576e+10;
        return (int) Math.floor(yearsBetween); 
    }
}

