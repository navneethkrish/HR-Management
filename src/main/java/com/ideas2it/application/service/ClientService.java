package com.ideas2it.application.service;

import java.util.List;

import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.model.Client;
import com.ideas2it.application.model.Project;

/**
 * ClientService
 * <p>
 *  It act as blueprint of implementing class and which shows some fuctionality 
 *  of that class.
 * <p>
 * @author Navaneeth 
 */
public interface ClientService {   
    
    /**
     * <p>
     *  Used to  get project to assign clients. 
     * </p>
     *
     * @param   Projectid    project id that is used to search project from 
     *                       project details.
     * @return  project      details of seacrhed project.
     */
    public Project getProjectById(int projectId) throws ApplicationException;
    
    /**
     * <p>
     *  Used to  get projects to assign clients. 
     * </p>
     *
     * @return  projects      details of projects.
     */
    public List<Project> getProjects( ) throws ApplicationException; 
    /**
     * <p>
     *  Used to get client details and added to clients details. 
     * </p>
     *
     * @param   client       client that is used to store in to
     *                       client details.
     * @return  int          Returns client Id. 
     */
    public int addClient(Client client) throws ApplicationException;
  
    /**
     * <p>
     *  Used to delete particular client from 
     *  the clients using client id. 
     * </p>
     *
     * @param   clientId    clientId (unique) that is used to delete
     *                      particular client details from  clients details.
     * @return  boolean     deletes client and return boolean
     *                      and it indicates wheather it is delete
     *                      or not.
     */  
    public boolean removeClientById(int clientId) throws ApplicationException;

    /**
     * <p>
     *  Used to fetch all client details
     * </p>
     *
     * @return  clients      Retrives all the client details. 
     */  
    public  List <Client> fetchClients() throws ApplicationException; 
    
    /**
     * <p>
     *  Used to Update a particular client details from 
     *  the clients details 
     * </p>
     *
     * @param    client        client that is used to store 
     *                         client details.
     *
     * @return  boolean        It checks the client Id and updates the 
     *                         clients details  if it is present and 
     *                         returns  boolean value.
     */
    public boolean modifyClient(Client client)  throws ApplicationException ;
    
    /**
     * <p>
     *  Used to search particular client from 
     *  the clients details using client id. 
     * </p>
     *
     * @param  clientId     clientId (unique) that is used to search
     *                      particular client details from  clients details.
     * @return client       Returns client which have a searched client details
     */ 
    public Client searchClientById(int clientId)  throws ApplicationException; 
    
    /**
     * <p>
     *  Used to restore particular client from 
     *  the deleted clients details using client id. 
     * </p>
     *
     * @param  clientId     clientId (unique) that is used to restore
     *                      particular client details from  deleted clients details.
     * @return  client      Returns client which have a restored client details
     */  
    public boolean restoreClientById(int clientId) throws ApplicationException;

    /**
     * <p>
     *  Used to fetch all deleted client details
     * </p>
     *
     * @return  clients      Retrives all the client details. 
     */  
    public List<Client> retrieveDeletedClients() throws ApplicationException;
 
    /**
     * <p>
     *  Used to add a projects to client. 
     * </p>
     * 
     * @param  client       Unique id of client.
     *
     * @param  projects     Projects (unique) that is used to add 
     *                      projects to client.
     * @return boolean      Returns boolean and indicates added or not.
     */
    public boolean addProjectsToClient(String projects[] ,  
                                     Client client) throws ApplicationException;

}
