package com.ideas2it.application.model;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


import org.springframework.format.annotation.DateTimeFormat; 

import com.ideas2it.application.commons.constants.Constants;
import com.ideas2it.application.model.Client;
import com.ideas2it.application.model.Employee;

/**
 * Employee
 * <p>
 *  Used to store a project details like project name, 
 *  client name and project id and no employees who involved in this project.   
 * </p>
 * @author Navanith
 */  
public class Project implements Serializable {
    private int id;
    private int status; 
    private String name;
    private Date startDate;
    private Date endDate;
    private Integer clientId;
    private List <Employee> employees = new ArrayList<Employee>();
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
  
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
     
    }
    public void setId(int id) {
       this.id = id; 
    }

    public void setName(String name) {
        this.name = name; 
    }

    public void setEmployees(List <Employee> employees) {
        this.employees = employees;
    }
    
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public int getId() {
        return this.id;
    }

    public Integer getClientId() {
        return this.clientId;  
    }

    public String getName() {
        return this.name;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getStartDate() {
        return this.startDate;  
    }
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getEndDate() {
        return this.endDate;  
    }
  
    public int getStatus() {
        return status;    
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public int hashcode() {
        return this.getId();  
    } 

    public boolean equals(Project project) {
        if(project == null) {
           return Boolean.FALSE;
        } 
        if(project == this) {
           return Boolean.TRUE;
        }
        return (this.getId() == project.getId());    
    }
}
