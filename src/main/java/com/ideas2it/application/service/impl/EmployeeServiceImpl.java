package com.ideas2it.application.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ideas2it.application.dao.EmployeeDao;
import com.ideas2it.application.dao.impl.EmployeeDaoImpl;
import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.model.Address;
import com.ideas2it.application.model.Employee;
import com.ideas2it.application.service.EmployeeService;

/**
 * EmployeeService
 * <p>
 *  Get Employee details and perform some business logics like add ,delete ,update
 *  etc... with concern Employees detail and access Employees data from data access layer.
 * <p>
 * @author Navaneeth 
 */
public class EmployeeServiceImpl implements EmployeeService {   
    private EmployeeDao employeeDao;
    
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    } 
  
    public EmployeeDao getEmployeeDao() {
        return this.employeeDao;
    } 

    /**
     * {@inheritDoc} 
     */
    public int addEmployee(Employee employee) throws ApplicationException {
        return employeeDao.storeEmployee(employee);             
    }

    /**
     * {@inheritDoc} 
     */
    public boolean removeEmployeeById(int employeeId) 
                                                   throws ApplicationException {
        Employee employee = this.searchEmployeeById(employeeId);
        if(null != employee) {
            for(Address address : employee.getAddresses()) {
                address.setStatus(0);
            }
            employee.getProjects().clear();
            return employeeDao.deleteEmployee(employee); 
        }
        return Boolean.FALSE;
    }

    /**
     * {@inheritDoc} 
     */ 
    public  List <Employee> fetchEmployees() throws ApplicationException {
        return employeeDao.retrieveEmployees();
    }
    
    /**  
     * {@inheritDoc} 
     */
    public boolean modifyEmployee(Employee employee) 
                                                   throws ApplicationException {
        return employeeDao.updateEmployee(employee); 
    }
    
    /**
     * {@inheritDoc} 
     */
    public Employee searchEmployeeById(int employeeId) 
                                                   throws ApplicationException {
        return employeeDao.searchEmployee(employeeId);
    }
     
    /**
     * {@inheritDoc} 
     */
    public boolean restoreEmployeeById(int employeeId) throws ApplicationException {
        Employee employee = employeeDao.searchDeletedEmployee(employeeId);
        if(null != employee) {
            return this.modifyEmployee(employee);
        }
        return Boolean.FALSE; 
    }
    
    /**
     * {@inheritDoc} 
     */
    public List<Employee> findEmployeesByDesignationAndExperience
                                          (String designation, int experience) 
                                               throws ApplicationException {
        return employeeDao.searchEmployeesByDesignationAndExperience
                                                       (designation, experience);
    }

    
    /**
     * {@inheritDoc} 
     */ 
    public  List <Employee> fetchDeletedEmployees() throws ApplicationException {
        return employeeDao.retrieveDeletedEmployees();
    }
}
  
