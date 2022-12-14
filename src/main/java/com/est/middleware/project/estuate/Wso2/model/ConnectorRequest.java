package com.est.middleware.project.estuate.Wso2.model;

import java.util.List;

import com.est.middleware.project.estuate.Wso2.entity.Connector;

public class ConnectorRequest {
	 private String organizationId;
	    private List<Connector> connectors;
	    
	    public String getOrganizationId() {
	        return this.organizationId;
	    }
	    
	    public void setOrganizationId(final String organizationId) {
	        this.organizationId = organizationId;
	    }
	    
	    public List<Connector> getConnectors() {
	        return this.connectors;
	    }
	    
	    public void setConnectors(final List<Connector> connectors) {
	        this.connectors = connectors;
	    }
	}


