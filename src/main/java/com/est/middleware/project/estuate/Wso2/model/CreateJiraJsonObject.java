package com.est.middleware.project.estuate.Wso2.model;

public class CreateJiraJsonObject {
private Fields fields;
    
    public CreateJiraJsonObject() {
    }
    
    public CreateJiraJsonObject(final Fields fields) {
        this.fields = fields;
    }
    
    public void setFields(final Fields fields) {
        this.fields = fields;
    }
    
    public Fields getFields() {
        return this.fields;
    }
}


