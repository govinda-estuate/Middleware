package com.est.middleware.project.estuate.Wso2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "connector_info")
public class ConnectorInfo {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "cinfo_srl_id")
	    private int cInfoSrlId;
	    @Column(name = "conn_info_id")
	    private String connectorInfoId;
	    @Column(name = "conn_info_name")
	    private String connectorInfoName;
	    
	    public int getcInfoSrlId() {
	        return this.cInfoSrlId;
	    }
	    
	    public void setcInfoSrlId(final int cInfoSrlId) {
	        this.cInfoSrlId = cInfoSrlId;
	    }
	    
	    public String getConnectorInfoId() {
	        return this.connectorInfoId;
	    }
	    
	    public void setConnectorInfoId(final String connectorInfoId) {
	        this.connectorInfoId = connectorInfoId;
	    }
	    
	    public String getConnectorInfoName() {
	        return this.connectorInfoName;
	    }
	    
	    public void setConnectorInfoName(final String connectorInfoName) {
	        this.connectorInfoName = connectorInfoName;
	    }
	}

	
	    
