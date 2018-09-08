package com.ideas2it.application.dao;

import java.util.List;
import java.util.Set;

import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.model.Employee;
import com.ideas2it.application.model.Project;

/**
 * EmployeeDao
 * <p>
 * It act as blueprint of implementing class and which shows some fuctionality 
 * of that class. 
 * </p>
 * @author Navanith
 */ 
public interface EmployeeDao {   

    /**
     * <p>
     *  Used to store a Employee details in to employees details 
     * </p>
     *
     * @param     employee     It is  employee object which have 
     *                         a employee details.
     *
     * @return   employeeId   Adds employee and returns Employee unique Id. 
     */   
    public int storeEmployee(Employee employee) throws ApplicationException;
   
    /**
     * 
     * <p>
     *  Used to retrives all Employees details  
     * </p>
     *
     * @return    Employees    Retrives the all Employee Details.
     */
    public List <Employee> retrieveEmployees() throws ApplicationException;
    
    /**
     * <p>
     *  Used to delete Employee details.  
     * </p>
     *
     * @param    employee      Employee object which have a employee details.
     *
     * @return   boolean       Returns boolean and indicates wheather employee
     *                         deleted or not. 
     */
    public boolean deleteEmployee(Employee employee) throws ApplicationException;

    /**
     *<p>
     * Used to search particular Employee from 
     * the employees. 
     *</p>
     *
     * @param  employeeId      Employee Id that is used to search
     *                         a particular employee from Employees.
     * @return employee        Returns employee which have a employee details.
     */
    public Employee searchEmployee(int employeeId)  throws ApplicationException;
    
    /**
     * <p>
     *  Used to Update a particular Employee details from 
     *  the employees details 
     * </p>
     *
     * @param   employee      Updated employee details.
     *
     * @return  boolean       It checks the employeeId and updates the 
     *                        employee details  if it is present and 
     *                        returns  boolean value.
     */
    public boolean updateEmployee(Employee employee) throws ApplicationException;
    
    /**
     *<p>
     * Used to search particular Employee from 
     * the deleted employees. 
     *</p>
     *
     * @param  employeeId      Employee Id that is used to search
     *                         a particular employee from deleted Employees.
     * @return employee        Returns employee which have a employee details.
     */
    public Employee searchDeletedEmployee(int employeeId) 
                                                    throws ApplicationException;
     
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
    public List <Employee> searchEmployeesByDesignationAndExperience
                                             (String designation, int experience) 
                                                   throws ApplicationException;
    
    /**
     * <p>
     *  Used to retrives all deleted Employees details  
     * </p>
     *
     * @return    Employees    Retrives the all deleted Employee Details.
     */
     public List<Employee> retrieveDeletedEmployees()  throws ApplicationException;

}
