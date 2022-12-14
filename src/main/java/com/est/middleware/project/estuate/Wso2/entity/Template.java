package com.est.middleware.project.estuate.Wso2.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

public class Template {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "tmp_auto_id")
	    private int tmpSrlId;
	    @Column(name = "connector_id")
	    private String connectorId;
	    @Lob
	    @Column(name = "tmp_static")
	    private String staticTemplate;
	    @Column(name = "tmp_dynamic")
	    private String dynamicTemplate;
	    
	    public int getTmpSrlId() {
	        return this.tmpSrlId;
	    }
	    
	    public void setTmpSrlId(final int tmpSrlId) {
	        this.tmpSrlId = tmpSrlId;
	    }
	    
	    public String getStaticTemplate() {
	        return this.staticTemplate;
	    }
	    
	    public void setStaticTemplate(final String staticTemplate) {
	        this.staticTemplate = staticTemplate;
	    }
	    
	    public String getDynamicTemplate() {
	        return this.dynamicTemplate;
	    }
	    
	    public void setDynamicTemplate(final String dynamicTemplate) {
	        this.dynamicTemplate = dynamicTemplate;
	    }
	    
	    public String getConnectorId() {
	        return this.connectorId;
	    }
	    
	    public void setConnectorId(final String connectorId) {
	        this.connectorId = connectorId;
	    }
	}


