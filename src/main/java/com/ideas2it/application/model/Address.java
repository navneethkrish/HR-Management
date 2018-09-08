package com.ideas2it.application.model;

/**
 * Address
 * <p>
 *  Used to store a Address details like door No, city name,   
 *  Address of client or employee or any other address details.   
 * </p>
 * @author Navanith 
 */
public class Address {
    private int id;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private int status;
    private String type;
    private Integer employeeId;
    private Integer clientId;
    
    public void setType (String type) {
        this.type = type;
    }
   
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
   
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    } 

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo; 
    }

    public void setCity(String city) {
        this.city = city;
    }
     
    public int getStatus() {
        return this.status;    
    }
    public String getType (){
        return this.type;
    }
    public int getId() {
        return this.id; 
    }
    public String  getAddressLineOne() {
        return this.addressLineOne;
    } 
  
    public String  getAddressLineTwo() {
        return this.addressLineTwo;
    } 
   
    public String getCity() {
        return this.city;
    }
   
    public Integer getEmployeeId() {
        return this.employeeId;
    }
   
    public Integer getClientId() {
        return this.clientId;
    }
}  



