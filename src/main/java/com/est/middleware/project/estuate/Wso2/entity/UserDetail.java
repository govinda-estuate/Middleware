package com.est.middleware.project.estuate.Wso2.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDetail {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_auto_id")
	    private int srlId;
	    @Column(name = "first_name")
	    private String firstName;
	    @Column(name = "last_name")
	    private String lastName;
	    @Column(name = "user_name")
	    private String userName;
	    @Column(name = "email_id")
	    private String emailId;
	    @Column(name = "password")
	    private String password;
	    @Column(name = "created_user")
	    private String createdUser;
	    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+5:30")
	    @Temporal(TemporalType.DATE)
	    @Column(name = "created_date")
	    private Date createdDate;
	    @Column(name = "modified_user")
	    private String modifiedUser;
	    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+5:30")
	    @Temporal(TemporalType.DATE)
	    @Column(name = "modified_date")
	    private Date modifiedDate;
	    @Column(name = "active")
	    private Character isActive;
	    
	    public int getSrlId() {
	        return this.srlId;
	    }
	    
	    public void setSrlId(final int srlId) {
	        this.srlId = srlId;
	    }
	    
	    public String getFirstName() {
	        return this.firstName;
	    }
	    
	    public void setFirstName(final String firstName) {
	        this.firstName = firstName;
	    }
	    
	    public String getLastName() {
	        return this.lastName;
	    }
	    
	    public void setLastName(final String lastName) {
	        this.lastName = lastName;
	    }
	    
	    public String getUserName() {
	        return this.userName;
	    }
	    
	    public void setUserName(final String userName) {
	        this.userName = userName;
	    }
	    
	    public String getEmailId() {
	        return this.emailId;
	    }
	    
	    public void setEmailId(final String emailId) {
	        this.emailId = emailId;
	    }
	    
	    public String getPassword() {
	        return this.password;
	    }
	    
	    public void setPassword(final String password) {
	        this.password = password;
	    }
	    
	    public String getCreatedUser() {
	        return this.createdUser;
	    }
	    
	    public void setCreatedUser(final String createdUser) {
	        this.createdUser = createdUser;
	    }
	    
	    public Date getCreatedDate() {
	        return this.createdDate;
	    }
	    
	    public void setCreatedDate(final Date createdDate) {
	        this.createdDate = createdDate;
	    }
	    
	    public String getModifiedUser() {
	        return this.modifiedUser;
	    }
	    
	    public void setModifiedUser(final String modifiedUser) {
	        this.modifiedUser = modifiedUser;
	    }
	    
	    public Date getModifiedDate() {
	        return this.modifiedDate;
	    }
	    
	    public void setModifiedDate(final Date modifiedDate) {
	        this.modifiedDate = modifiedDate;
	    }
	    
	    public Character getIsActive() {
	        return this.isActive;
	    }
	    
	    public void setIsActive(final Character isActive) {
	        this.isActive = isActive;
	    }
	    
	    @Override
	    public String toString() {
	        return "UserDetail [srlId=" + this.srlId + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", userName=" + this.userName + ", emailId=" + this.emailId + ", password=" + this.password + ", createdUser=" + this.createdUser + ", createdDate=" + this.createdDate + ", modifiedUser=" + this.modifiedUser + ", modifiedDate=" + this.modifiedDate + ", isActive=" + this.isActive + "]";
	    }
	}


