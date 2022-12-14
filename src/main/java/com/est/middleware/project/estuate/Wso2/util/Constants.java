package com.est.middleware.project.estuate.Wso2.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.est.middleware.project.estuate.Wso2.config.DatabaseConfig;

public class Constants {
	@Autowired
    private DatabaseConfig databaseConfig;
    public static final int SUCCESS = 1;
    public static final int FAIL = 0;
    public static final String CREATE_JIRA_ISSUE = "CreateIssue";
    public static final String GET_JIRA_ISSUE = "GetIssue";
    public static final String GET_DEVICE42 = "GetDevice";
    public static final String CREATE_JIRA_ISSUE_WSO2_URL = "/createIssue";
    public static final String GET_JIRA_ISSUE_WSO2_URL = "/getIssue/issue/{id}";
    public static final String GET_DEVICE42_TESTING_URL = "https://192.168.0.50/api/1.0/devices/name/{name}";
    public static final String CREATE_SF_CASE_URL = "https://cunning-shark-6tfo6l-dev-ed.my.salesforce.com/services/data/v42.0/sobjects/Case";
    public static final String GET_SF_ACCESS_TOKEN_URL = "https://cunning-shark-6tfo6l-dev-ed.my.salesforce.com/services/oauth2/token";
    public static final String GET_SF_CASE_URL = "https://cunning-shark-6tfo6l-dev-ed.my.salesforce.com/services/data/v20.0/query";
    
    public static String getStackTrace(final Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }
}


