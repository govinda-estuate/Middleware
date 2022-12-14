package com.est.middleware.project.estuate.Wso2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "connector")
public class Connector implements Serializable {
	 private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "conn_auto_id")
	    private int connSrlId;
	    @Column(name = "conn_id")
	    private String connectorId;
	    @Column(name = "conn_name")
	    private String connectorName;
	    @Column(name = "org_id")
	    private String organization_id;
	    @Column(name = "active")
	    private Character isActive;
	    
	    public Connector(final String connectorId, final String connectorName) {
	        this.connectorId = connectorId;
	        this.connectorName = connectorName;
	    }
	    
	    public Connector() {
	    }
	    
	    public int getConnSrlId() {
	        return this.connSrlId;
	    }
	    
	    public void setConnSrlId(final int connSrlId) {
	        this.connSrlId = connSrlId;
	    }
	    
	    public String getConnectorId() {
	        return this.connectorId;
	    }
	    
	    public void setConnectorId(final String connectorId) {
	        this.connectorId = connectorId;
	    }
	    
	    public String getConnectorName() {
	        return this.connectorName;
	    }
	    
	    public void setConnectorName(final String connectorName) {
	        this.connectorName = connectorName;
	    }
	    
	    public String getOrganization_id() {
	        return this.organization_id;
	    }
	    
	    public void setOrganization_id(final String organization_id) {
	        this.organization_id = organization_id;
	    }
	    
	    public Character getIsActive() {
	        return this.isActive;
	    }
	    
	    public void setIsActive(final Character isActive) {
	        this.isActive = isActive;
	    }
	    
	    @Override
	    public String toString() {
	        return "Connector [connSrlId=" + this.connSrlId + ", connectorId=" + this.connectorId + ", connectorName=" + this.connectorName + ", organization_id=" + this.organization_id + ", isActive=" + this.isActive + "]";
	    }
	}


