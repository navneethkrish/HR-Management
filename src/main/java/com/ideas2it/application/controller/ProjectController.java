package com.ideas2it.application.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
  
import com.ideas2it.application.commons.constants.Constants;
import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.model.Project;
import com.ideas2it.application.model.Employee;
import com.ideas2it.application.logger.ApplicationLogger;
import com.ideas2it.application.service.EmployeeService;
import com.ideas2it.application.service.impl.EmployeeServiceImpl;
import com.ideas2it.application.service.impl.ProjectServiceImpl;
import com.ideas2it.application.service.ProjectService;
import com.ideas2it.application.utils.stringutils.StringUtils; 

/**
 * ProjectController
 * <p>
 *  Used to get project details like id , name, start Date , end date and involved 
 *  employees details are get from user and it act as interface user.  
 * <P>
 *
 * @author Navaneeth. 
 */ 
@Controller
public class ProjectController {

    public static final long serialVersionUID = 1L;
   
    private static ProjectService  projectService;
 
    public void setProjectService(ProjectService  projectService) { 
        this.projectService = projectService;
    }
 
    public ProjectService getProjectService() {
        return this.projectService;
    }
   
    /**
     * <p>
     *   Used to create a plain new Project Object and which is used to load 
     *   project form to client View.
     * </p>
     *    
     * @return   ModelAndView        Used to represents the View which will be 
     *                               displayed to the client.
     */
    @RequestMapping(value =Constants.CERATE_PROJECT, method = RequestMethod.GET) 
    public ModelAndView getProject() { 
        ModelAndView modelAndView = new ModelAndView
                                                (Constants.CREATE_PAGE_PROJECT);
        Project project = new Project();
        modelAndView.addObject(Constants.PROJECT, project);
        System.out.println(project);      
        return modelAndView; 
     }
    
    /**
     * <p>
     *   Used to get a project details like project name, start date, end date
     *   etc.. which is get from client View and send it to add operation 
     *   and send response back to Client view whether it is added or not.    
     * </p>
     *  
     * @param     project            It is a project object which have a 
     *                               project details. 
     *
     * @return   ModelAndView        Used to represents the View which will be 
     *                               displayed to the client.
     */
    @RequestMapping(value = Constants.ADD_PROJECT, method = RequestMethod.POST) 
    public ModelAndView createProject(@ModelAttribute(Constants.PROJECT) Project project) { 
        ModelAndView modelAndView = this.getProject();
        try {
            int projectId = projectService.addProject(project);
            modelAndView.addObject(Constants.Id, projectId);
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());
        }     
        return modelAndView; 
    }
    
    /**
     * <p>
     *   Used to get a particular project Id which is get from Client View 
     *   and send it to delete operation and send response back to Client view 
     *   whether it is deleted or not.  
     * </p>
     *
     * @param      request          Request which is send by user/client.
     * 
     * @param      response         Response which is respond by server. 
     *
     * @return     ModelAndView     Used to represents the View which will be 
     *                              displayed to the client.
     */
    @RequestMapping(value =Constants.DELETE_PROJECT, method = RequestMethod.POST) 
    private ModelAndView deleteProject(HttpServletRequest request,
	                                            HttpServletResponse response)  {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_PAGE_PROJECT);
        try { 
            int projectId = Integer.parseInt(request.getParameter
                                                          (Constants.DELETEID));
            if (projectService.deleteProjectById(projectId)) {
                modelAndView.addObject(Constants.DELETE_MSG , 1); 
            } else {
                modelAndView.addObject( Constants.DELETE_MSG , 0); 
            }     
            modelAndView.addObject(Constants.PROJECTS,
                                              projectService.fetchAllProject());
        } catch (ApplicationException e) {
           return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());
        }
        return modelAndView;
    } 
    
    /**
     * <p>
     *  Used to display all project details to client view.  
     * </p>
     *
     * @param      request          Request which is send by user/client.
     * 
     * @param      response         Response which is respond by server. 
     *
     * @return     ModelAndView     Used to represents the View which will be 
     *                              displayed to the client.
     */
    @RequestMapping(value =Constants.DISPLAY_PROJECT, method = RequestMethod.GET) 
    private ModelAndView displayProjects(HttpServletRequest request,
	                                             HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_PAGE_PROJECT);
        try {
            modelAndView.addObject(Constants.PROJECTS ,
                                              projectService.fetchAllProject());   
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());  
        }
        return modelAndView;
    } 
    
    /**
     * <p>
     *   Used to get a updated project details like project name, start date , 
     *   End date...etc which is get from client view and send it 
     *   to update operation and send response back to Client view whether it is 
     *   updated or not.  
     * </p>
     *
     * @param      project         It is a project object which have a 
     *                             updated project details. 
     *
     * @param      request         Request which is send by user/client.
     *
     * @param      response        Response which is respond by server. 
     *
     * @return     ModelAndView    Used to represents the View which will be 
     *                             displayed to the client.
     */
    @RequestMapping(value =Constants.UPDATE_PROJECT, method = RequestMethod.POST) 
    private ModelAndView getUpdatedProject(@ModelAttribute(Constants.PROJECT) Project project,
                     HttpServletRequest request, HttpServletResponse response) {
        project.setStatus(1);
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_PAGE_PROJECT);
        String allEmployee[] = request.getParameterValues(Constants.SEL);
        String associatedEmployees[] = request.getParameterValues(Constants.UNSEL);
        try {
            projectService.addEmployeesToProject(associatedEmployees, project);
            projectService.addEmployeesToProject(allEmployee, project);
            if (projectService.modifyProject(project)) {
                modelAndView.addObject(Constants.UPDATE_MSG, 1);
              
            } else {
                modelAndView.addObject(Constants.UPDATE_MSG, 0);
            }
            modelAndView.addObject(Constants.PROJECTS, 
                                              projectService.fetchAllProject()); 
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());
        }
        return modelAndView;  
    }
    
    /**
     * <p>
     *  Used to show project details to client view for edit and get updated details
     *  from client.  
     * </p>
     *
     * @param       request        Request which is send by user/client.
     *
     * @param       response       Response which is respond by server.
     *
     * @return     ModelAndView    Used to represents the View which will be 
     *                             displayed to the client.  
     */
    @RequestMapping(value =Constants.GET_PROJECT, method = RequestMethod.POST) 
    private ModelAndView displayProjectToUpdate (HttpServletRequest request,
	                                             HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(
                                                 Constants.CREATE_PAGE_PROJECT);
        Project project = new Project();
        Employee employee = null;
        List <Employee> employees = null;
        try {
            employees = projectService.getEmployees();
            int projectId = Integer.parseInt(request.getParameter
                                                          (Constants.UPDATEID));
            project = projectService.searchProjectById(projectId); 
            if (null != project) {
                for (Employee associatedEmployee : project.getEmployees()) {
                    for (Employee employeeIndex : employees)  {
                        if (employeeIndex.getId() == associatedEmployee.getId()) {
                            employee = employeeIndex;
                            break;       
                        }  
                    } 
                    employees.remove(employee);
                }
                modelAndView.addObject(Constants.UPADTEL_MSG, 1);
                modelAndView.addObject(Constants.PROJECT, project);
                modelAndView.addObject(Constants.ASSOEMPLOYEES, 
                                                       project.getEmployees());      
                modelAndView.addObject(Constants.EMPLOYEES, employees);
            }     
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());
        } 
        return modelAndView;    
    }
    
    /**
     * <p>
     *   Used to get a particular  Project Id which is get from client View 
     *   and send it to search operation and send response back to Client view 
     *   whether it is present or not.  
     * </p>
     *
     * @param      request         Request which is send by user/client.
     *
     * @param      response        Response which is respond by server. 
     *
     * @return     ModelAndView    Used to represents the View which will be 
     *                             displayed to the client.
     */
    @RequestMapping(value = Constants.SEARCH_PROJECT, method = RequestMethod.POST) 
    private ModelAndView searchProject(HttpServletRequest request,
	                                             HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_PAGE_PROJECT);
        Project project = null; 
        Set <Project>  projects = null;
        try {
            projects = new HashSet<Project>();
            if (0 != request.getParameter( Constants.Id).length()) {
                int projectId = Integer.parseInt(request.getParameter
                                                            (Constants.Id));
                project = projectService.searchProjectById(projectId); 
                projects.add(project);
            }    
            if (null != project) {
                modelAndView.addObject( Constants.PROJECTS, projects); 
            } else {
                modelAndView.addObject( Constants.SEARCH , 0); 
                modelAndView.addObject( Constants.PROJECTS, projectService.
                                                            fetchAllProject());
            }     
        }  
        catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());
        }   
        return modelAndView;
    }
}
