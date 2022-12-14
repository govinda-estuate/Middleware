package com.est.middleware.project.estuate.Wso2.model;

import java.util.List;

public class DefaultRequestBody {
	 private String host;
	    private String connector;
	    private int ticketId;
	    private String domain;
	    private String action;
	    private List<RequestFieldValues> fields;
	    
	    public String getAppId() {
	        return this.host;
	    }
	    
	    public void setAppId(final String appId) {
	        this.host = appId;
	    }
	    
	    public int getTicketId() {
	        return this.ticketId;
	    }
	    
	    public void setTicketId(final int ticketId) {
	        this.ticketId = ticketId;
	    }
	    
	    public String getDomain() {
	        return this.domain;
	    }
	    
	    public void setDomain(final String domain) {
	        this.domain = domain;
	    }
	    
	    public String getAction() {
	        return this.action;
	    }
	    
	    public void setAction(final String action) {
	        this.action = action;
	    }
	    
	    public List<RequestFieldValues> getFields() {
	        return this.fields;
	    }
	    
	    public void setFields(final List<RequestFieldValues> fields) {
	        this.fields = fields;
	    }
	    
	    public String getHost() {
	        return this.host;
	    }
	    
	    public void setHost(final String host) {
	        this.host = host;
	    }
	    
	    public String getConnector() {
	        return this.connector;
	    }
	    
	    public void setConnector(final String connector) {
	        this.connector = connector;
	    }
	}


