package com.est.middleware.project.estuate.Wso2.model;

public class Device42Dto {
	 private String name;
	    private int serial_number;
	    private String last_updated;
	    private String type;
	    private String is_it_virtual_host;
	    private String asset_number;
	    private String service_level;
	    private String virtual_host_name;
	    private int responseStatus;
	    
	    public String getName() {
	        return this.name;
	    }
	    
	    public void setName(final String name) {
	        this.name = name;
	    }
	    
	    public int getSerial_number() {
	        return this.serial_number;
	    }
	    
	    public void setSerial_number(final int serial_number) {
	        this.serial_number = serial_number;
	    }
	    
	    public String getLast_updated() {
	        return this.last_updated;
	    }
	    
	    public void setLast_updated(final String last_updated) {
	        this.last_updated = last_updated;
	    }
	    
	    public String getType() {
	        return this.type;
	    }
	    
	    public void setType(final String type) {
	        this.type = type;
	    }
	    
	    public String getIs_it_virtual_host() {
	        return this.is_it_virtual_host;
	    }
	    
	    public void setIs_it_virtual_host(final String is_it_virtual_host) {
	        this.is_it_virtual_host = is_it_virtual_host;
	    }
	    
	    public String getAsset_number() {
	        return this.asset_number;
	    }
	    
	    public void setAsset_number(final String asset_number) {
	        this.asset_number = asset_number;
	    }
	    
	    public String getService_level() {
	        return this.service_level;
	    }
	    
	    public void setService_level(final String service_level) {
	        this.service_level = service_level;
	    }
	    
	    public String getVirtual_host_name() {
	        return this.virtual_host_name;
	    }
	    
	    public void setVirtual_host_name(final String virtual_host_name) {
	        this.virtual_host_name = virtual_host_name;
	    }
	    
	    public int getResponseStatus() {
	        return this.responseStatus;
	    }
	    
	    public void setResponseStatus(final int responseStatus) {
	        this.responseStatus = responseStatus;
	    }
	}


