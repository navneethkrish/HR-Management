package com.ideas2it.application.dao.impl;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.dao.ProjectDao;
import com.ideas2it.application.hibernetUtils.SessionFactoryUtil;
import com.ideas2it.application.model.Project;
import com.ideas2it.application.logger.ApplicationLogger;

/**
 * ProjectDaoImpl
 * <p>
 *  It act as database of project and used manipulate project details
 *  from projects details. 
 * </p>
 * @author Navanith.
 */
public class ProjectDaoImpl implements ProjectDao  {
    private static final String ADD_ERROR_INFO = 
                                             "ERROR WHILE ADDING PROJECT NAME:"; 
    private static final String SEARCH_ERROR_INFO = 
                                             "ERROR WHILE RETRIEVE PROJECT ID:";
    private static final String DELETE_ERROR_INFO = 
                                             "ERROR WHILE DELETE PROJECT ID:";
    private static final String DISPLAY_ERROR_INFO = 
                                             "ERROR WHILE DISPLAY PROJECTS";
    private static final String UPDATE_ERROR_INFO = 
                                             "ERROR WHILE UPDATE PROJECT ID";
    private static final String ID ="id";
    private static final String STATUS ="status";  
    
    /**
     * {@inheritDoc} 
     */
    public int storeProject(Project project) throws ApplicationException{
        Session session = null;
        int projectId =0;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Transaction transcation = session.beginTransaction();
		    session.save(project);
            projectId = project.getId(); 
            transcation.commit();
          
        } catch (HibernateException e) {
            ApplicationLogger.error(ADD_ERROR_INFO+project.getName(), e);
            throw new ApplicationException(ADD_ERROR_INFO+project.getName()); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }
        return projectId;
    }

    /**
     * {@inheritDoc} 
     */
    public List<Project> retrieveProjects() throws ApplicationException {
        Session session = null;
        List listOfProjects = null;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Criteria criteria = session.createCriteria(Project.class);
            criteria.add(Restrictions.eq(STATUS,1)); 
            listOfProjects = criteria.list();
        } catch (HibernateException e) {
            ApplicationLogger.error(DISPLAY_ERROR_INFO, e);
            throw new ApplicationException(DISPLAY_ERROR_INFO); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }    
	    return listOfProjects; 
    }
    
    /**
     * {@inheritDoc} 
     */
    public boolean deleteProject(Project project) throws ApplicationException {
        Session session = null;
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Transaction transcation = session.beginTransaction();
            project.setStatus(0);
		    session.update(project);
            transcation.commit(); 
            return Boolean.TRUE;
        } catch (HibernateException e) {
            ApplicationLogger.error(DELETE_ERROR_INFO+project.getId(), e);
            throw new ApplicationException(DELETE_ERROR_INFO+project.getId()); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }   
    }
    
    /**
     * {@inheritDoc} 
     */
    public Project searchProject(int projectId) throws ApplicationException {
        Session session = null;
        Project project = null; 
        try {
            session = SessionFactoryUtil.getInstance().getSession();
            Criteria criteria = session.createCriteria(Project.class);
            criteria.add(Restrictions.eq(ID, projectId));
            criteria.add(Restrictions.eq(STATUS,1));
            project = (Project) criteria.uniqueResult();
        } catch (HibernateException e) {
            ApplicationLogger.error(SEARCH_ERROR_INFO+projectId, e);
            throw new ApplicationException(SEARCH_ERROR_INFO+projectId); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }   
        return project; 
    }
   
    /**
     * {@inheritDoc} 
     */
    public boolean updateProject(Project project) throws ApplicationException {
        Session session = null;
        try {
             session = SessionFactoryUtil.getInstance().getSession();
             Transaction transcation = session.beginTransaction();
		     session.merge(project);
             transcation.commit(); 
             return Boolean.TRUE;
        } catch (HibernateException e) {
            ApplicationLogger.error(UPDATE_ERROR_INFO+project.getId(), e);
            System.out.println(e);
            throw new ApplicationException(UPDATE_ERROR_INFO+project.getId()); 
        } finally {
            if(null != session ) {
                session.close();
            }
        }    
    }
}

