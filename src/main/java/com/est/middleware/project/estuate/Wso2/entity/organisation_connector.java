package com.est.middleware.project.estuate.Wso2.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class organisation_connector {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "srl_auto_id")
	    private int srlId;
	    @Column(name = "org_id")
	    private String organization_id;
	    @Column(name = "connector_id")
	    private String connector_id;
	    
	    public int getSrlId() {
	        return this.srlId;
	    }
	    
	    public void setSrlId(final int srlId) {
	        this.srlId = srlId;
	    }
	    
	    public String getOrganization_id() {
	        return this.organization_id;
	    }
	    
	    public void setOrganization_id(final String organization_id) {
	        this.organization_id = organization_id;
	    }
	    
	    public String getConnector_id() {
	        return this.connector_id;
	    }
	    
	    public void setConnector_id(final String connector_id) {
	        this.connector_id = connector_id;
	    }
	}


