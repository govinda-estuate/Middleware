package com.est.middleware.project.estuate.Wso2.util;

public enum RequestParamEnum {
	KEY("txtKey"), 
    SUMMARY("txtsummary"), 
    NAME("txtTask"), 
    DEVICE_NAME("DeviceName"), 
    CREATE_JIRA_ACTION("CreateIssueForm"), 
    LINK_DEVICE_ACTION("linkDeviceForm"), 
    LINK_ISSUE_ACTION("LinkIssueForm"), 
    ISSUE_ID("IssueID"), 
    CONTACT_ID("contactId"), 
    STATUS("status"), 
    ORIGIN("origin"), 
    GET_SF_CASE_ACTION("getSFCaseForm"), 
    SF_CASE_NUMBER("caseNumber");
    
    private String params;
    
    private RequestParamEnum(final String params) {
        this.params = params;
    }
    
    public String getParams() {
        return this.params;
    }
}


