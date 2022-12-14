package com.est.middleware.project.estuate.Wso2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oganization")
public class Organisation {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "org_auto_id")
	    private int orgSrlId;
	    @Column(name = "org_id")
	    private String organizationId;
	    @Column(name = "org_name")
	    private String organizationName;
	    
	    public Organisation(final String organizationId, final String organizationName) {
	        this.organizationId = organizationId;
	        this.organizationName = organizationName;
	    }
	    
	    public Organisation() {
	    }
	    
	    public int getOrgSrlId() {
	        return this.orgSrlId;
	    }
	    
	    public void setOrgSrlId(final int orgSrlId) {
	        this.orgSrlId = orgSrlId;
	    }
	    
	    public String getOrganizationId() {
	        return this.organizationId;
	    }
	    
	    public void setOrganizationId(final String organizationId) {
	        this.organizationId = organizationId;
	    }
	    
	    public String getOrganizationName() {
	        return this.organizationName;
	    }
	    
	    public void setOrganizationName(final String organizationName) {
	        this.organizationName = organizationName;
	    }
	    
	    @Override
	    public String toString() {
	        return "Organization [orgSrlId=" + this.orgSrlId + ", organizationId=" + this.organizationId + ", organizationName=" + this.organizationName + "]";
	    }
	}
