package com.ideas2it.application.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.application.commons.constants.Constants;    
import com.ideas2it.application.dao.EmployeeDao;
import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.hibernetUtils.SessionFactoryUtil;
import com.ideas2it.application.logger.ApplicationLogger;
import com.ideas2it.application.model.Employee;
import com.ideas2it.application.model.Project;
    
/**
 * EmployeeDaoImpl
 * <p>
 *  It act as database of employee and used manipulate employee details
 *  from Employees details. 
 * </p>
 * @author Navanith.
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private static final String ADD_ERROR_INFO = "ERROR WHILE ADDING EMPNAME:"; 
    private static final String SEARCH_ERROR_INFO ="ERROR WHILE RETRIEVE EMPID:";
    private static final String DELETE_ERROR_INFO = "ERROR WHILE DELETE EMPID:";
    private static final String DISPLAY_ERROR_INFO = 
                                                "ERROR WHILE DISPLAY EMPLOYEES";
    private static final String UPDATE_ERROR_INFO = "ERROR WHILE UPDATE EMPID";
    private static final String ID ="id";
    private static final String STATUS ="status";
    private static final String EXPERIENCE ="experience";
    private static final String DESIGNATION ="designation";
    
    /**
     * {@inheritDoc} 
     */
    public int storeEmployee(Employee employee) throws ApplicationException {
        int employeeId = 0;
        Session session = null;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Transaction transcation = session.beginTransaction();
		    session.save(employee);
            employeeId = employee.getId();
            transcation.commit();
        } catch (HibernateException e) {
            ApplicationLogger.error(ADD_ERROR_INFO+employee.getName(), e);
            throw new ApplicationException(ADD_ERROR_INFO+employee.getName());
        } finally {
            if(null != session ) {
                session.close();
            } 
        } 
        return employeeId; 
    }

    /**
     * {@inheritDoc} 
     */
    public List<Employee> retrieveEmployees() throws ApplicationException {
        Session session = null;
        List listOfEmployees = null;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq(STATUS,1));
            listOfEmployees = criteria.list(); 
            return listOfEmployees;
        } catch (HibernateException e) {
            ApplicationLogger.error(DISPLAY_ERROR_INFO, e);
            throw new ApplicationException(DISPLAY_ERROR_INFO); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }    
	}
    
    /**
     * {@inheritDoc} 
     */
    public boolean deleteEmployee(Employee employee) 
                                                   throws ApplicationException {
        Session session = null; 
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Transaction transcation = session.beginTransaction();
            employee.setStatus(0);       
   		    session.update(employee);
            transcation.commit(); 
            return Boolean.TRUE;
        } catch (HibernateException e) {
            ApplicationLogger.error(DELETE_ERROR_INFO+employee.getId(), e);
            throw new ApplicationException(DELETE_ERROR_INFO+employee.getId());
        } finally {
            if(null != session ) {
                session.close();
            }
        }   
    }
   
    /**
     * {@inheritDoc} 
     */
    public Employee searchEmployee(int employeeId) throws ApplicationException {
        Session session = null;
        Employee employee = null;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq(ID, employeeId));
            criteria.add(Restrictions.eq(STATUS,1));
            employee = (Employee) criteria.uniqueResult();
            if(null != employee) {
                employee.getProjects();
            }
            return employee;
        } catch (HibernateException e) {
            ApplicationLogger.error(SEARCH_ERROR_INFO+employeeId, e);
            throw new ApplicationException(SEARCH_ERROR_INFO+employeeId); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }   
    }

    /**
     * {@inheritDoc} 
     */
    public boolean updateEmployee(Employee employee) 
                                                   throws ApplicationException {
        Session session = SessionFactoryUtil.getInstance().getSession(); 
        try {
             session = SessionFactoryUtil.getInstance().getSession();
             Transaction transcation = session.beginTransaction();
             employee.setStatus(1);
		     session.update(employee);
             transcation.commit(); 
             return Boolean.TRUE;
        } catch (HibernateException e) {
            ApplicationLogger.error(UPDATE_ERROR_INFO+employee.getId(), e);
            throw new ApplicationException(UPDATE_ERROR_INFO+employee.getId()); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }   
    }
    
    /**
     * {@inheritDoc} 
     */
    public Employee searchDeletedEmployee(int employeeId) 
                                                   throws ApplicationException {
        Session session = null;
        Employee employee = null;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq(ID, employeeId));
            criteria.add(Restrictions.eq(STATUS,0));
            employee = (Employee) criteria.uniqueResult();
            if(null != employee) {
                employee.getProjects();
            }
            return employee;
        } catch (HibernateException e) {
            ApplicationLogger.error(SEARCH_ERROR_INFO+employeeId, e);
            throw new ApplicationException(SEARCH_ERROR_INFO+employeeId); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }   
    }
   
    /**
     * {@inheritDoc} 
     */
    public List<Employee> searchEmployeesByDesignationAndExperience
                                             (String designation, int experience) 
                                                   throws ApplicationException {
        Session session = null;
        List listOfEmployees = null;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq(DESIGNATION, designation));
            criteria.add(Restrictions.eq(EXPERIENCE, experience));
            criteria.add(Restrictions.eq(STATUS,0));
            listOfEmployees = criteria.list(); 
            return listOfEmployees;
        } catch (HibernateException e) {
            ApplicationLogger.error(DISPLAY_ERROR_INFO, e);
            throw new ApplicationException(DISPLAY_ERROR_INFO); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }    
    }
 
    /**
     * {@inheritDoc} 
     */
    public List<Employee> retrieveDeletedEmployees()  throws ApplicationException {
        Session session = null;
        List listOfEmployees = null;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq(STATUS,0));
            listOfEmployees = criteria.list(); 
            return listOfEmployees;
        } catch (HibernateException e) {
            ApplicationLogger.error(DISPLAY_ERROR_INFO, e);
            throw new ApplicationException(DISPLAY_ERROR_INFO); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }    
    }
}
