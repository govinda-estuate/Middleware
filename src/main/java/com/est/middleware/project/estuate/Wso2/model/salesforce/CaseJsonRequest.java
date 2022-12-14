package com.est.middleware.project.estuate.Wso2.model.salesforce;

public class CaseJsonRequest {
	private String ContactId;
    private String Status;
    private String Origin;
    
    public String getContactId() {
        return this.ContactId;
    }
    
    public String getStatus() {
        return this.Status;
    }
    
    public String getOrigin() {
        return this.Origin;
    }
    
    public void setContactId(final String ContactId) {
        this.ContactId = ContactId;
    }
    
    public void setStatus(final String Status) {
        this.Status = Status;
    }
    
    public void setOrigin(final String Origin) {
        this.Origin = Origin;
    }
}


