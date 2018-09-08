package com.ideas2it.application.model;

import java.io.Serializable;
import java.util.ArrayList; 
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.format.annotation.DateTimeFormat; 

import com.ideas2it.application.model.Project;
import com.ideas2it.application.model.Address;
import com.ideas2it.application.utils.dateutils.DateUtils; 


/**
 * Employee
 * <p>
 * Used to store a Employee details like employee name,DOB,id,emailId 
 * phone no and salary.  
 * </p>
 * @author Navanith.
 */ 
public class Employee implements Serializable {
    private long phoneNo;
    private double salary;
    private int id;
    private String name;
    private String emailId;
    private String designation;
    private Date DOB;
    private Date dateOfJoining;
    private int status; 
    private int rating;
    private List <Address> addresses = new ArrayList<Address>(); 
    private Set <Project> projects = new HashSet <Project>();
  
    /**
     * Getters and Setters
     */
    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }  
    
    public void setDesignation(String designation) {
        this.designation = designation;  
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    } 
     
    public void setDOB(Date dob) {
        this.DOB = dob;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo; 
    }
    
    public void setSalary(double salary) {
        this.salary = salary;  
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setProjects(Set <Project> projects) {
        this.projects = projects;
    }
    
    public void setAddresses(List <Address> addresses) {
        this.addresses = addresses;
    }
   
    public List<Address> getAddresses() {
        return this.addresses;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getDateOfJoining() {
        return this.dateOfJoining;
    }  
    
    public int getExperience() {
        return DateUtils.getDateDifference(dateOfJoining); 
    }
    
    public String getDesignation() {
        return this.designation;  
    }
    
    public int getRating() {
        return this.rating;
    } 

    public int getId() {
        return this.id;
    }

    public int getAge() {
        return DateUtils.getDateDifference(DOB);
    }

    public long getPhoneNo() {
        return this.phoneNo;
    }

    public double getSalary() {
        return this.salary;
    }

    public String getName() {
        return this.name;
    }

    public String getEmailId() {
        return this.emailId;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getDOB() {
        return this.DOB; 
    }

    public Set <Project> getProjects() {
        return this.projects;
    }

    public int getStatus() {
        return status;    
    }
    
    public int hashcode() {
        return this.getId();
    }

    public boolean equals(Employee employee)
    {
        if(null == employee) {
            return Boolean.FALSE;
        }
        if(this == employee) {
            return Boolean.TRUE;
        }
	    if(this.getId() == employee.getId())
	        return Boolean.TRUE;
	    return Boolean.FALSE;
    }
} 
