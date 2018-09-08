package com.ideas2it.application.model;

/**
 * User
 * <p>
 *  Used to store a login details like password ,username and privileges of 
 *  user.   
 * </p>
 * @author Navanith 
 */
public class User {
    private int id;
    private String emailId;   
    private String password;
    private String privileges;
     
    public void setId(int id) {
        this.id = id;
    } 

    public void setEmailId (String emailId) {
        this.emailId = emailId;
    } 

    public void setPassword (String password) {
        this.password = password; 
    }
    
    public void setPrivileges (String privileges) {
        this.privileges = privileges;
    }
  
    public int getId() {
        return this.id;   
    } 

    public String getEmailId() {
        return this.emailId;
    } 
 
    public String getPassword() {
        return this.password;  
    } 
 
    public String getprivileges() {
        return this.privileges;
    }
}
