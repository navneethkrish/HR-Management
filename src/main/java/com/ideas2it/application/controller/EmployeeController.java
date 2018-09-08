package com.ideas2it.application.controller;

import java.util.HashSet;
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
import com.ideas2it.application.logger.ApplicationLogger;
import com.ideas2it.application.model.Address;
import com.ideas2it.application.model.Employee;
import com.ideas2it.application.service.EmployeeService;
import com.ideas2it.application.service.impl.EmployeeServiceImpl;
import com.ideas2it.application.utils.dateutils.DateUtils;
import com.ideas2it.application.utils.stringutils.StringUtils;

/**
 * EmployeeController
 * <p>
 *  Represents a Employee enrolled in the company and
 *  All employee details like name ,DOB ,DOJ, designation ..etc are get from 
 *  user and managed in this class.  
 * <P>
 *
 * @author Navaneeth 
 */ 
@Controller
public class EmployeeController {

    private static final long serialVersionUID = 1L;
  
    private  ModelAndView modelAndView = null;
    
    private  static EmployeeService  employeeService;
 
    public void setEmployeeService(EmployeeService  employeeService) { 
        this.employeeService = (EmployeeService) employeeService;
    }
 
    public EmployeeService getEmployeeService() {
       return this.employeeService;
    }
    
    /**
     * <p>
     *   Used to create a plain new Employee Object and which is used to load 
     *   employee form  to client View.
     * </p>
     *    
     * @return   ModelAndView        Used to represents the View which will be 
     *                               displayed to the client.
     */
    @RequestMapping(value= Constants.CREATE_EMPLOYEE , method = RequestMethod.GET) 
    public ModelAndView loadEmployeeView() { 
        modelAndView = new ModelAndView(Constants.CREATE_PAGE);
        Employee employee = new Employee();
        Address personalAddress = new Address();
        personalAddress.setType(Constants.PERSONALADDRESS);
        personalAddress.setStatus(1);
        Address officeAddress = new Address();
        officeAddress.setType(Constants.OFFICEADDRESS);
        officeAddress.setStatus(1);
        employee.getAddresses().add(personalAddress);
        employee.getAddresses().add(officeAddress);
        modelAndView.addObject(Constants.EMPLOYEEUI, employee);
        return modelAndView; 
    }

    /**
     * <p>
     *   Used to get a Employee details like Employee name, dob, salary, emailid
     *   designation which is get from client View and send it to add operation 
     *   and send response back to Client view whether it is addedd or not.    
     * </p>
     *  
     * @param     Employee           It is a employee object which have a 
     *                               employee details. 
     *
     * @return   ModelAndView        Used to represents the View which will be 
     *                               displayed to the client.
     */
    @RequestMapping(value = Constants.ADD_EMPLOYEE, method = RequestMethod.POST)
    public ModelAndView createEmployee(@ModelAttribute(Constants.EMPLOYEEUI) 
                                                           Employee employee ) {
        ModelAndView modelAndView = this.loadEmployeeView();
        try {
            employee.setStatus(1); 
            modelAndView.addObject(Constants.Id,  
                                          employeeService.addEmployee(employee));
            
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage()); 
        } 
        return modelAndView;      
    } 
    
    /**
     * <p>
     *   Used to get a particular Employee Id which is get from Client View 
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
    @RequestMapping(value = Constants.DELETE_EMPLOYEE, method = RequestMethod.POST)
    public ModelAndView deleteEmployee(HttpServletRequest request,
	                                            HttpServletResponse response)  {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_PAGE); 
        try {
            int employeeId = Integer.parseInt(
                                      request.getParameter(Constants.DELETEID));
            if (employeeService.removeEmployeeById(employeeId)) {
                modelAndView.addObject(Constants.DELETE_MSG, 1); 
            } else {
                modelAndView.addObject(Constants.DELETE_MSG, 0); 
            }
            modelAndView.addObject(Constants.EMPLOYEES ,
                                              employeeService.fetchEmployees());       
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());  
        }
        return modelAndView;      
    }
 
    /**
     * <p>
     *   Used to get a updated Employee details like Employee name, dob, salary, 
     *   emailid designation...etc which is get from client view and send it 
     *   to update operation and send response back to Client view whether it is 
     *   updated or not.  
     * </p>
     * 
     * @param      Employee           It is a employee object which have a 
     *                                updated employee details. 
     *
     * @return     ModelAndView       Used to represents the View which will be 
     *                                displayed to the client.
     */
    @RequestMapping(value = Constants.UPDATE_EMPLOYEE, method = RequestMethod.POST)
    private  ModelAndView getUpdatedEmployee(@ModelAttribute(Constants.EMPLOYEEUI) 
                                                            Employee employee) {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_PAGE);
        try {
            if (employeeService.modifyEmployee(employee)){
                modelAndView.addObject(Constants.EMPLOYEES, 
                                              employeeService.fetchEmployees()); 
                modelAndView.addObject(Constants.UPDATE_MSG, 1);
            }
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage()); 
        }  
        return modelAndView;
    }
    
    /**
     * <p>
     *   Used to get a particular Employee Id which is get from client View 
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
    @RequestMapping(value =Constants.SERACH_EMPLOYEE, 
                                                    method = RequestMethod.POST)
    private ModelAndView getEmployee(HttpServletRequest request,
	                                             HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_PAGE);
        Employee employee = null;  
        Set<Employee> employees = new HashSet<Employee>();  
        try { 
            if (0 != request.getParameter(Constants.Id).length()) {
                int employeeId = Integer.parseInt(request.getParameter
                                                                (Constants.Id));
                employee = employeeService.searchEmployeeById(employeeId);
                employees.add(employee);
            } 
            if (null == employee) {
                modelAndView.addObject(Constants.SEARCH, 0); 
            } else {
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
     *  Used to display all Employee details to client view.  
     * </p>
     *
     * @return     ModelAndView    Used to represents the View which will be 
     *                             displayed to the client.
     */
    @RequestMapping(value =Constants.DISPLAY_EMPLOYEE, method = RequestMethod.GET)
    private ModelAndView displayEmployees() {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_PAGE);
        try {
            modelAndView.addObject(Constants.EMPLOYEES, employeeService.fetchEmployees());
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());        
        }
        return modelAndView;
    }
    
    /**
     * <p>
     *  Used to display all deleted Employee details to client view.  
     * </p>
     *
     * @return     ModelAndView    Used to represents the View which will be 
     *                             displayed to the client.
     */
    @RequestMapping(value = Constants.DISPLAY_DELETED_EMPLOYEE, 
                                                     method = RequestMethod.GET)
    private ModelAndView displayDeletedEmployees() {
        ModelAndView modelAndView = new ModelAndView(Constants.RESTORE_PAGE);
        try {
            modelAndView.addObject(Constants.EMPLOYEES, 
                                       employeeService.fetchDeletedEmployees());
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());       
        }
        return modelAndView;
    }
 
    /**
     * <p>
     *  Used to show employee details to client view for edit and get updated details
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
    @RequestMapping(value =Constants.GET_EMPLOYEE, method = RequestMethod.POST)
    private ModelAndView displayEmployeeToUpdate(HttpServletRequest request,
	                                             HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(Constants.CREATE_PAGE);
        Employee employee = null;
        int employeeId = 0;
        try {
            employeeId = Integer.parseInt(request.getParameter(Constants.UPDATEID));
            employee = employeeService.searchEmployeeById(employeeId); 
            if (null != employee) {
                modelAndView.addObject(Constants.EMPLOYEEUI, employee);
                modelAndView.addObject(Constants.UPADTEL_MSG, 1);          
            } 
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage()); 
        } 
        return modelAndView;
    }
    
    /**
     * <p>
     *  Used to get deleted employees details which is get from client View and 
     *  sent it to restore operation and send response back to Client view 
     *  whether it is restored or not.    
     * </p>
     *
     * @param     request         Request which is send by user/client.
     *
     * @param     response        Response which is respond by server.
     * 
     * @return    ModelAndView    Used to represents the View which will be 
     *                            displayed to the client.    
     */
    @RequestMapping(value =Constants.RESTORE_EMPLOYEE, method = RequestMethod.POST)
    private ModelAndView restoreEmployee(HttpServletRequest request,
	                                             HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(Constants.RESTORE_PAGE);  
        try {
            String restoreEmployeeId[] = 
                                      request.getParameterValues(Constants.SEL);
            if (null != restoreEmployeeId) {
                for (int index = 0; index < restoreEmployeeId.length; index++) {
                    employeeService.restoreEmployeeById
                                (Integer.parseInt(restoreEmployeeId[index]));
                }
            }
            modelAndView.addObject(Constants.UPDATE_MSG , 1); 
            modelAndView.addObject(Constants.EMPLOYEES,
                                       employeeService.fetchDeletedEmployees()); 
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage()); 
        }  
        return modelAndView;
    }
}
