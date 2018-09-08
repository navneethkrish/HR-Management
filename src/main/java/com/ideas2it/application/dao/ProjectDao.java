package com.ideas2it.application.dao;

import java.util.List;
import java.util.Set;
 
import org.hibernate.Session;

import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.model.Employee;     
import com.ideas2it.application.model.Project;

/**
 * ProjectDao
 * <p>
 * It act as blueprint of implementing ProjectDao and which shows some 
 * fuctionality of that class. 
 * </p>
 * @author Navanith
 */
public interface ProjectDao {
    
    /**
     * <p>
     *  Used to store a Project details  
     * </p>
     *
     * @param   Project     Project which have 
     *                      a project details.
     * @return  int        Returns Project Id.
     */ 
    public int storeProject(Project project) throws ApplicationException;

    /**
     * <p>
     *  Used to retrives all project details  
     * </p>
     *
     * @return  projects   Retrives the all project Details.
     */
    public List<Project> retrieveProjects() throws ApplicationException;
    
    /**
     * <p>
     *  Used to remove a particular project details.  
     * </p>
     *
     * @param  project     project which have a project details.
     *
     * @return boolean     Returns booelan and indicates it is deleted or not. 
     */
    public boolean deleteProject(Project project) throws ApplicationException;
    
    /**
     * <p>
     *  Used to search a particular project details from 
     *  the projects details 
     * </p>
     *
     * @param   project id  Unique id of project used to search.
     *                             
     * @return  project     project which have searched project details. 
     */
    public Project searchProject(int projectId) throws ApplicationException;
    
    /**
     * <p>
     *  Used to Update a particular project details from 
     *  the projects details 
     * </p>
     *
     * @param   Project     Project which have a project details.
     *  
     * @return  boolean     It checks the project Id and updates the 
     *                      projects details  if it is present and 
     *                      returns  boolean value.
     */
    public boolean updateProject(Project project) throws ApplicationException;
}
