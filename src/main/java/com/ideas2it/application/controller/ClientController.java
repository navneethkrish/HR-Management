package com.ideas2it.application.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.ideas2it.application.model.Client;
import com.ideas2it.application.model.Project;
import com.ideas2it.application.service.ClientService;
import com.ideas2it.application.service.impl.ClientServiceImpl;
import com.ideas2it.application.service.impl.ProjectServiceImpl;
import com.ideas2it.application.service.ProjectService;
import com.ideas2it.application.utils.stringutils.StringUtils;

/**
 * ClientController
 * <p>
 *  Used to get client details like id , name, email , phone no and involved 
 *  Projects details are get from user and it act as interface user.  
 * <P>
 *
 * @author Navaneeth. 
 */ 
@Controller
public class ClientController { 
    private static ClientService clientService;
   
    private static final long serialVersionUID = 1L;
 
    public void setClientService(ClientService clientService) { 
        this. clientService =  clientService;
    }
 
    public ClientService getClientService() {
       return this. clientService;
    }
    
   /**
     * <p>
     *   Used to create a plain new client Object and which is used to load 
     *   client form to client View.
     * </p>
     *    
     * @return    ModelAndView        Used to represents the View which will be 
     *                                displayed to the client.
     */
    @RequestMapping(value = Constants.CREATE_CLIENT, method = RequestMethod.GET) 
    public ModelAndView loadClientPage() { 
        ModelAndView modelAndView = new ModelAndView
                                                 (Constants.CREATE_PAGE_CLIENT);
        Client client = new Client();
        Address personalAddress = new Address();
        personalAddress.setType(Constants.CLIENTPERSONALADDRESS);
        personalAddress.setStatus(1);
        Address officeAddress = new Address();
        officeAddress.setType(Constants.CLIENTOFFICEADDRESS);
        officeAddress.setStatus(1);
        client.getAddresses().add(personalAddress);
        client.getAddresses().add(officeAddress);  
        modelAndView.addObject(Constants.CLIENTUI, client);
        return modelAndView; 
     }  

    /**
     * <p>
     *   Used to get a client details like client name, requirement, phoneNo
     *   etc.. which is get from client View and send it to add operation 
     *   and send response back to Client view whether it is added or not.    
     * </p>
     *  
     * @param     client             It is a client object which have a 
     *                               client details.
     * @return   ModelAndView        Used to represents the View which will be 
     *                               displayed to the client.
     */
    @RequestMapping(value = Constants.ADD_CLIENT, method = RequestMethod.POST) 
    public ModelAndView createClient(@ModelAttribute(Constants.CLIENTUI) Client client) { 
        ModelAndView modelAndView = this.loadClientPage();
        try {
            client.setStatus(1);
            int clientId = clientService.addClient(client);
            modelAndView.addObject(Constants.Id, clientId);
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());
        }     
        return modelAndView;
     }
    
    /**
     * <p>
     *   Used to get a particular client Id which is get from Client View 
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
    @RequestMapping(value = Constants.DELETE_CLIENT, method = RequestMethod.POST) 
    private ModelAndView deleteClient(HttpServletRequest request,
	                                             HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView
                                                (Constants.DISPLAY_PAGE_CLIENT);
        try { 
            int clientId = Integer.parseInt(request.getParameter
                                                          (Constants.DELETEID));
            if (clientService.removeClientById(clientId)) {
                modelAndView.addObject(Constants.DELETE_MSG , 1); 
            } else {
                modelAndView.addObject( Constants.DELETE_MSG , 0); 
            }  
            modelAndView.addObject(Constants.CLIENTS,
                                                  clientService.fetchClients());    
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());
        }
        return modelAndView;
    }  
 
    /**
     * <p>
     *   Used to get a updated project details like client name, requirement , 
     *   phoneno...etc which is get from client view and send it 
     *   to update operation and send response back to Client view whether it is 
     *   updated or not.  
     * </p>
     *
     * @param      client          It is a client object which have a updated
     *                             client details.
     *
     * @param      request         Request which is send by user/client.
     *
     * @param      response        Response which is respond by server. 
     *
     * @return     ModelAndView    Used to represents the View which will be 
     */
    @RequestMapping(value =Constants.UPDATE_CLIENT, method = RequestMethod.POST) 
    private ModelAndView getUpdatedClient(@ModelAttribute(Constants.CLIENTUI) 
        Client client, HttpServletRequest request, HttpServletResponse response) {
        client.setStatus(1);
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_PAGE_CLIENT);
        String allProjects[] = request.getParameterValues("unselect");
        System.out.println(allProjects+"*****************&&&&&&&&&**************************\n\n\n\n\n\n\n");
        String associatedProjects[] = request.getParameterValues("selected");
        System.out.println(associatedProjects+"*******************************************\n\n\n\n\n\n");  
        try {
            clientService.addProjectsToClient(allProjects ,client);
            //clientService.addProjectsToClient(associatedProjects ,client); 
            if (clientService.modifyClient(client)) {
                modelAndView.addObject(Constants.UPDATE, 1);
            } else {
                modelAndView.addObject(Constants.UPDATE, 0);
            } 
            modelAndView.addObject(Constants.CLIENTS ,
                                              clientService.fetchClients());
        } catch (ApplicationException e) {
             return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());
        } 
        return modelAndView; 
    }    
    
    /**
     * <p>
     *   Used to get a particular  client Id which is get from client View 
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
    @RequestMapping(value =Constants.SEARCH_CLIENT, method = RequestMethod.POST) 
    private ModelAndView searchClient(HttpServletRequest request,
	                                             HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_PAGE_CLIENT);
        Client client = null; 
        List <Client>  clients = null;
        try {
            clients = new ArrayList<Client>();
            if (0 != request.getParameter( Constants.Id).length()) {
                int clientId = Integer.parseInt(request.getParameter
                                                            (Constants.Id));
                client = clientService.searchClientById(clientId); 
                clients.add(client);
            }    
            if (null != client) {
                modelAndView.addObject( Constants.CLIENTS, clients);  
            } else {
                modelAndView.addObject( Constants.SEARCH , 0); 
            }     
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
     * @param      request         Request which is send by user/client.
     *
     * @param      response        Response which is respond by server. 
     *
     * @return     ModelAndView    Used to represents the View which will be 
     *                             displayed to the client.
     */
    @RequestMapping(value =Constants.DISPLAY_CLIENT, method = RequestMethod.GET)   
    private ModelAndView displayClients(HttpServletRequest request,
	                                            HttpServletResponse response)  {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_PAGE_CLIENT);
        try {
            modelAndView.addObject(Constants.CLIENTS ,
                                                  clientService.fetchClients());   
        } catch (ApplicationException e) {
             return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());
        }
        return modelAndView;
    } 
    
    /**
     * <p>
     *  Used to show client details to client view for edit and get updated details
     *  from client view.  
     * </p>
     *
     * @param       request        Request which is send by user/client.
     *
     * @param       response       Response which is respond by server.
     *
     * @return     ModelAndView    Used to represents the View which will be 
     *                             displayed to the client.  
     */
    @RequestMapping(value =Constants.GET_CLIENT, method = RequestMethod.POST)   
    private ModelAndView displayClientToUpdate(HttpServletRequest request,
	                                             HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(Constants.CREATE_PAGE_CLIENT);
        Client client = null;
        List <Project> projects = new ArrayList();         
        try {
            if (null != request.getParameter(Constants.UPDATEID)) {
                int  clientId = Integer.parseInt(request.getParameter
                                                          (Constants.UPDATEID));
                client = clientService.searchClientById(clientId); 
            }
            if (null != client) { 
                for (Project project : clientService.getProjects()) {
                    if (null == project.getClientId()) {
                        projects.add(project);
                    } 
                }  
                modelAndView.addObject(Constants.UPADTEL_MSG , 1);
                modelAndView.addObject(Constants.CLIENTUI, client);
                modelAndView.addObject(Constants.ASSOPROJECTS , 
                                                          client.getProjects()); 
                modelAndView.addObject(Constants.PROJECTS , projects);
                
            } else {
                modelAndView.addObject(Constants.UPADTEL_MSG , 0); 
            }     
        } catch (ApplicationException e) {
             return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());
        }
        return modelAndView;     
    }
   
    
    /**
     * <p>
     *  Used to display all deleted client details to client view.  
     * </p>
     *
     * @return     ModelAndView    Used to represents the View which will be 
     *                             displayed to the client.
     */
    @RequestMapping(value = Constants.DISPLAY_DELETED_CLIENT, 
                                                     method = RequestMethod.GET)
    private ModelAndView displayDeletedClients() {
        ModelAndView modelAndView = new ModelAndView(Constants.RESTORE_CLIENT_PAGE);
        try {
            modelAndView.addObject(Constants.CLIENTS, 
                                        clientService.retrieveDeletedClients());
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage());       
        }
        return modelAndView;
    }

    /**
     * <p>
     *  Used to get deleted clients details which is get from client View and 
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
    @RequestMapping(value =Constants.RESTORE_CLIENT, method = RequestMethod.POST)
    private ModelAndView restoreClient(HttpServletRequest request,
	                                             HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(Constants.RESTORE_CLIENT_PAGE);  
        try {
            String restoreClientId[] = request.getParameterValues(Constants.SEL);
            if (null != restoreClientId) {
                for (int index = 0; index < restoreClientId.length; index++) {
                    clientService.restoreClientById
                                     (Integer.parseInt(restoreClientId[index]));
                }
            }
            modelAndView.addObject(Constants.UPDATE_MSG , 1); 
            modelAndView.addObject(Constants.CLIENTS,
                                        clientService.retrieveDeletedClients()); 
        } catch (ApplicationException e) {
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR ,
                                                                e.getMessage()); 
        }  
        return modelAndView;
    }
}
