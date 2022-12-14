package com.est.middleware.project.estuate.Wso2.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.domain.EntityScan;

 @EntityScan
public class Device42 {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "device_auto_id")
	    private int deviceSrlId;
	    @Column(name = "device_name")
	    private String deviceName;
	    @Column(name = "connector_id")
	    private String connectorId;
	    @Column(name = "ticket_id")
	    private int ticketId;
	    @Column(name = "organization_id")
	    private String organizationId;
	    
	    public int getDeviceSrlId() {
	        return this.deviceSrlId;
	    }
	    
	    public void setDeviceSrlId(final int deviceSrlId) {
	        this.deviceSrlId = deviceSrlId;
	    }
	    
	    public String getDeviceName() {
	        return this.deviceName;
	    }
	    
	    public void setDeviceName(final String deviceName) {
	        this.deviceName = deviceName;
	    }
	    
	    public String getConnectorId() {
	        return this.connectorId;
	    }
	    
	    public void setConnectorId(final String connectorId) {
	        this.connectorId = connectorId;
	    }
	    
	    public int getTicketId() {
	        return this.ticketId;
	    }
	    
	    public void setTicketId(final int ticketId) {
	        this.ticketId = ticketId;
	    }
	    
	    public String getOrganizationId() {
	        return this.organizationId;
	    }
	    
	    public void setOrganizationId(final String organizationId) {
	        this.organizationId = organizationId;
	    }
	}


