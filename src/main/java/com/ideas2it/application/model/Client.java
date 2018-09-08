package com.ideas2it.application.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ideas2it.application.model.Address;
import com.ideas2it.application.model.Project;  

 
/**
 * Client
 * <p>
 *  Used to store a Client details like Client name, 
 *  client name and Client id and no projects involved.   
 * </p>
 * @author Navanith.
 */
public class Client {
    private int id; 
    private String name;
    private String requirement;
    private long phoneNo;
    private int status;
    private Set<Project> projects = new HashSet<Project>();
    private List<Address> addresses = new ArrayList<Address>();
   
    /**
     * Getter and Setters...
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setProjects(Set <Project> projects) {
        this.projects =projects;
    }
    
    public void setAddresses(List <Address> addresses) {
        this.addresses = addresses;
    }
   
    public void setStatus(int status) {
        this.status = status;
    }
    
    public List<Address> getAddresses() {
        return this.addresses;
    }

    public int getId() {
        return this.id;
    }
   
    public String getName() {
        return this.name;
    }
  
    public String getRequirement() {
        return this.requirement;
    }
   
    public Set<Project> getProjects() {
        return this.projects;
    }
  
    public long getPhoneNo() {
        return this.phoneNo; 
    }
   
    public int getStatus() {
        return this.status;
    } 

    public int hashcode() {
        return this.getId();
    }

    public boolean equals(Client client) {
        if(null == client) {
            return Boolean.FALSE;
        }
        if(this == client) {
            return Boolean.TRUE;
        }
	    if(this.getId() == client.getId())
	        return Boolean.TRUE;
	    return Boolean.FALSE;
    }
}   

