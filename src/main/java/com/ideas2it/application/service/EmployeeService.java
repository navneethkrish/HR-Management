package com.ideas2it.application.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.model.Employee;
import com.ideas2it.application.model.Project;

/**
 * EmployeeService
 * <p>
 *  It act as blueprint of implementing class and  which shows some fuctionality 
 *  of that class. 
 * <p>
 * @author Navaneeth 
 */
public interface EmployeeService {
    
    /**
     * <p>
     *  Used to get Employee details from 
     *  the employee and added to the employees details. 
     * </p>
     *
     * @param  employee     Employee that is used to store 
     *                      a employee details.
     * @return employeeId   Adds employee and returns Employee unique Id. 
     */
    public int addEmployee(Employee employee) throws ApplicationException;   
    
    /**
     * <p>
     *  Used to remove particular employee details from 
     *  the employees details 
     * </p>
     *
     * @param  employeeId   EmployeeId (unique) that is used to remove 
     *                      particular employee details from Employees details.
     * @return  boolean     Returns a boolean and indicates wheather its 
     *                                                          removed or not.
     */ 
    public  boolean removeEmployeeById(int employeeId) 
                                                    throws ApplicationException;
    
    /**
     * <p>
     *  Used to fetch all Employee details
     * </p>
     *
     * @return  Employees   Retrives all the employee details. 
     */
    public  List <Employee> fetchEmployees() throws ApplicationException;
    
    /**
     * <p>
     *  Used to Update a particular Employee details from 
     *  the employees details 
     * </p>
     *
     * @param   employee    Employee that is used to update 
     *                      a employee details.
     * @return  boolean     It checks the employeeId and updates the 
     *                      employee details  if it is present and 
     *                      returns  boolean value.
     */
    public boolean modifyEmployee(Employee employee)
                                                    throws ApplicationException;
    /**
     * <p>
     *  Used to search particular Employee details from 
     *  the employees details 
     * </p>
     *
     * @param  employeeId   EmployeeId (unique) that is used to search 
     *                      particular employee details from Employees details.
     * @return employee     Returns a seacrhed employee details.
     */ 
    public Employee searchEmployeeById(int employeeId) 
                                                    throws ApplicationException;
    /**
     * <p>
     *  Used to restore particular Employee details from 
     *  the deleted employees details 
     * </p>
     *
     * @param  employeeId   EmployeeId (unique) that is used to restore 
     *                      particular employee details from deleted Employees
     *                      details.
     * @return employee     Returns a seacrhed employee details.
     */ 
    public boolean restoreEmployeeById(int employeeId) throws ApplicationException;
     
    /**
     * <p>
     *  Used to fetch all Employee details by designation and Experince.
     * </p>
     * @param   designation designation of employee.
     *
     * @param   Experience  Experience of employee. 
     *
     * @return  Employees   Retrives all the employee details. 
     */ 
    public List<Employee> findEmployeesByDesignationAndExperience
                                            (String designation, int experience) 
                                                    throws ApplicationException;

     /**
     * <p>
     *  Used to fetch all deleted Employee details
     * </p>
     *
     * @return  Employees   Retrives all the deleted employee details. 
     */
     public  List <Employee> fetchDeletedEmployees() throws ApplicationException;
}
