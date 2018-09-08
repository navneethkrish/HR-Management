package com.ideas2it.application.service;

import java.util.List;
import java.util.Set;

import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.model.Employee;
import com.ideas2it.application.model.Project;

/**
 * ProjectService
 * <p>
 *  It act as blueprint of implementing project service class 
 *  and which shows some fuctionality of that class. 
 * <p>
 * @author Navaneeth 
 */
public interface ProjectService{
    
    /**
     * <p>
     *  Used to get employee a particular to assign project. 
     * </p>
     *
     * @param  employeeid   employee id that is used to search employee from 
     *                      employee details.
     * @return employee     details of employee.
     */
    public Employee getEmployeeById(int employeeId) throws ApplicationException;
    
    /**
     * <p>
     *  Used to get employees a particular to assign project. 
     * </p>
     *
     * @return employees     details of employees.
     */
    public List<Employee> getEmployees() throws ApplicationException;

    /**
     * <p>
     *  Used to get project details from 
     *  the user and added to projects details. 
     * </p>
     *
     * @param  projects     project that is used to store in to  
     *                      projects details.
     * @return int          Returns project Id.
     */
    public int addProject(Project project) throws ApplicationException;

    /**
     * <p>
     *  Used to fetch all project details
     * </p>
     *
     * @return  Set<Project>      Retrives all the project details. 
     */ 
    public List<Project> fetchAllProject() throws ApplicationException;

    /**
     * <p>
     *  Used to delete particular project from 
     *  the projects using project id. 
     * </p>
     *
     * @param  projectId    ProjectId (unique) that is used to delete
     *                      particular project details from  projects details.
     * @return project      Returns project which have a project details
     */ 
    public boolean deleteProjectById(int projectId) throws ApplicationException;

    /**
     * <p>
     *  Used to search particular project from 
     *  the projects details using project id. 
     * </p>
     *
     * @param  projectId    ProjectId (unique) that is used to search
     *                      particular project details from  projects details.
     * @return project      Returns project which have a project details
     */ 
    public Project searchProjectById(int projectId) throws ApplicationException;

    /**
     * <p>
     *  Used to Update a particular project details from 
     *  the projects details 
     * </p>
     *
     * @param   project            Updated project details.
     *  
     * @return  boolean            It checks the project Id and updates the 
     *                             projects details  if it is present and 
     *                             returns  boolean value.
     */
    public boolean modifyProject(Project projectId) throws ApplicationException ;

    /**
     * <p>
     *  Used to Update a particular project details from 
     *  the projects details 
     * </p>
     *
     * @param    Employee ID       Employees want to add project.
     * 
     * @param    project           Updated project details.
     *
     *  
     * @return   boolean           It checks the project Id and updates the 
     *                             projects details  if it is present and 
     *                             returns  boolean value.
     */
     public boolean addEmployeesToProject(String employeesID[] ,  
                                  Project project) throws ApplicationException;
     
}

