package com.ideas2it.application.service.impl;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

import com.ideas2it.application.dao.ClientDao;
import com.ideas2it.application.dao.impl.ClientDaoImpl;
import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.model.Address;
import com.ideas2it.application.model.Client;
import com.ideas2it.application.model.Project;
import com.ideas2it.application.service.ClientService;
import com.ideas2it.application.service.impl.ProjectServiceImpl;
import com.ideas2it.application.service.ProjectService;

/**
 * ClientService
 * <p>
 *  Get client details and perform some business logics like add ,delete ,update
 *  etc... with concern clients detail and access clients data from data access layer.  
 * <p>
 * @author Navaneeth 
 */
public class ClientServiceImpl implements ClientService {   
    private ClientDao clientDao;
    private ProjectService projectService;
    
    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    } 
    
    public  ClientDao getClientDao() {
        return this.clientDao;
    } 
    
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    } 
    
    public  ProjectService getProjectService() {
        return this.projectService;
    } 

    /**
     * {@inheritDoc} 
     */
    public Project getProjectById(int projectId) throws ApplicationException {
        return projectService.searchProjectById(projectId); 
    }
     
    /**
     * {@inheritDoc} 
     */
    public List<Project> getProjects() throws ApplicationException {
        return projectService.fetchAllProject(); 
    }
    /**
     * {@inheritDoc} 
     */
    public int addClient(Client client) throws ApplicationException {
        return clientDao.storeClient(client);             
    }

    /**
     * {@inheritDoc} 
     */
    public boolean removeClientById(int clientId) 
                                                   throws ApplicationException {
        Client client = this.searchClientById(clientId);
        if(null != client) { 
            for(Address address : client.getAddresses()) {
                address.setStatus(0);
            }   
            client.getProjects().clear();
            return clientDao.deleteClient(client); 
        }
        return Boolean.FALSE;
    }

    /**
     * {@inheritDoc} 
     */ 
    public  List <Client> fetchClients() throws ApplicationException {
        return clientDao.retrieveClients();
    }
    
    /**
     * {@inheritDoc} 
     */ 
    public boolean addProjectsToClient(String projects[] ,  
                                    Client client) throws ApplicationException {
        System.out.println("################");
        if(null != projects) {
            System.out.println("*******************************");
            for (int index = 0; index < projects.length ;index++) {
                System.out.println(client.getProjects().size()); 
                System.out.println(this.getProjectById
                                           (Integer.parseInt(projects[index]))+"******************");   
                client.getProjects().add(this.getProjectById
                                           (Integer.parseInt(projects[index]))); 
            }
            System.out.println("I am updating "+ clientDao.updateClient(client));
            System.out.println(client.getProjects());
            return true;
        }
        return Boolean.FALSE;
    }

    /**  
     * {@inheritDoc} 
     */
    public boolean modifyClient(Client client)  throws ApplicationException {
       return clientDao.updateClient(client);
    }
    
    /**
     * {@inheritDoc} 
     */
    public Client searchClientById(int clientId)  throws ApplicationException {
        return clientDao.searchClient(clientId);
    }
  
    /**
     * {@inheritDoc} 
     */ 
    public boolean restoreClientById(int clientId) throws ApplicationException {
        Client client = clientDao.searchDeletedClient(clientId);
        if(null != client) {
            return clientDao.updateClient(client);
        }
        return Boolean.FALSE;
    }
  
    /**
     * {@inheritDoc} 
     */ 
    public List<Client> retrieveDeletedClients() throws ApplicationException {
        return clientDao.fetchDeletedClients();
    }
}
