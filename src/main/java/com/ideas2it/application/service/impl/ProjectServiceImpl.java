package com.ideas2it.application.service.impl;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

import com.ideas2it.application.dao.impl.ProjectDaoImpl;
import com.ideas2it.application.dao.ProjectDao;
import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.model.Employee;
import com.ideas2it.application.model.Project;
import com.ideas2it.application.service.EmployeeService;
import com.ideas2it.application.service.impl.EmployeeServiceImpl;
import com.ideas2it.application.service.ProjectService;

/**
 * ProjectServiceImpl
 * <p>
 *  Get project details and perform some business logics like add ,delete ,update
 *  etc... with concern projects detail and access projects data from data access layer.
 * </p>
 *
 * @author Navanith.
 */ 
public class ProjectServiceImpl implements ProjectService {
    private  ProjectDao projectDao;
    private  EmployeeService employeeService;
    
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    } 
    
    public  ProjectDao getProjectDao() {
        return this.projectDao;
    }
    
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    } 
    
    public  EmployeeService getEmployeeService() {
        return this.employeeService;
    }
    
    /**
     * {@inheritDoc} 
     */ 
    public Employee getEmployeeById(int employeeId) throws 
                                                         ApplicationException  {
        return employeeService.searchEmployeeById(employeeId);
    }  
    
    /**
     * {@inheritDoc} 
     */
    public List<Employee> getEmployees() throws ApplicationException  {
        return employeeService.fetchEmployees();
    } 

    /**
     * {@inheritDoc} 
     */ 
    public int addProject(Project project) throws ApplicationException {
        project.setStatus(1);
        return projectDao.storeProject(project); 
    }
    
    /**
     * {@inheritDoc} 
     */ 
    public List<Project> fetchAllProject() throws ApplicationException  {
        return projectDao.retrieveProjects();
    }
 
    /**
     * {@inheritDoc} 
     */ 
    public Project searchProjectById(int projectId) throws ApplicationException {
        return  projectDao.searchProject(projectId);
    }
    
    /**
     * {@inheritDoc} 
     */ 
    public boolean addEmployeesToProject(String employees[] ,  
                                  Project project) throws ApplicationException {
        if(null != employees) {
            for (int index = 0; index < employees.length ;index++) {
                project.getEmployees().add(this.getEmployeeById
                                         (Integer.parseInt(employees[index]))); 
            }
            return projectDao.updateProject(project);
        }
        return Boolean.FALSE;
    }

    /**
     * {@inheritDoc} 
     */ 
    public boolean deleteProjectById(int projectId)
                                                   throws ApplicationException {
        Project project = projectDao.searchProject(projectId);
        if(null != project) {
            project.getEmployees().clear();
            project.setClientId(null);
            return projectDao.deleteProject(project);
        }
        return Boolean.FALSE;  
    }
    
    /**
     * {@inheritDoc} 
     */  
    public boolean modifyProject(Project project) throws ApplicationException {
        return projectDao.updateProject(project);
    }
}













