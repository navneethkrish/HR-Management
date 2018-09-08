package com.ideas2it.application.dao;

import java.util.List;
 
import org.hibernate.Session; 

import com.ideas2it.application.dao.ClientDao;
import com.ideas2it.application.exception.ApplicationException;
import com.ideas2it.application.model.Client;
import com.ideas2it.application.logger.ApplicationLogger;

/**
 * ClinetDao
 * <p>
 * It act as blueprint of implementing clientDaoImpl class and 
 * which shows some fuctionality of that class. 
 * </p>
 * @author Navanith
 */
public interface ClientDao {
    
    /**
     * <p>
     *  Used to store a Client details  
     * </p>
     *
     * @param   client     client object which have 
     *                     a client details.
     * @return  int        Returns client ID.
     */ 
    public int storeClient(Client client) throws ApplicationException;
    
    /**
     *  <p>
     *   Used to retrives all client details  
     *  </p>
     *
     *  @return  clients   Retrives the all client Details.
     */
    public List<Client> retrieveClients() throws ApplicationException ;
    
    /**
     *  <p>
     *   Used to remove Client details.  
     *  </p>
     *
     *   @param  client    client object which have a client details.
     *
     *   @return boolean   Returns booelan and indicates it is deleted or not. 
     */
    public boolean deleteClient(Client client) throws ApplicationException ;
    
    /**
     * <p>
     *  Used to search a particular client details from 
     *  the clients details 
     * </p>
     *
     * @param   clientid   Unique id of client used to search.
     *                             
     * @return  client     client which have a searched client details. 
     */
    public Client searchClient(int clientId) throws ApplicationException; 
    
    /**
     * <p>
     *  Used to Update a particular client details from 
     *  the clients details 
     * </p>
     *
     * @param   client     client which have a client details.
     *  
     * @return  boolean    It checks the client Id and updates the 
     *                     clients details  if it is present and 
     *                     returns  boolean value.
     */
    public boolean updateClient(Client client) throws ApplicationException;
    
    /**
     * <p>
     *  Used to search a particular client details from 
     *  the deleted clients details for restore. 
     * </p>
     *
     * @param   clientid   Unique id of client used to restore.
     *                             
     * @return  client     client which have a searched client details. 
     */
    public Client searchDeletedClient(int clientId) throws ApplicationException;
  
    /**
     *  <p>
     *   Used to retrives all deleted client details  
     *  </p>
     *
     *  @return  clients   Retrives the all deleted client Details.
     */
    public List<Client> fetchDeletedClients() throws ApplicationException;
 
}

