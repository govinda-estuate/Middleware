package com.est.middleware.project.estuate.Wso2.service;

import org.springframework.http.ResponseEntity;

import com.est.middleware.project.estuate.Wso2.model.CreateJiraJsonObject;

public interface Wso2Service {
	 ResponseEntity<String> getJiraIssue(final String issueId);
	    
	    ResponseEntity<String> createJiraIssue(final CreateJiraJsonObject requestBody);
	    
	    ResponseEntity<String> getDevice42(final String name);
	    
	    ResponseEntity<String> getSFCase(final String caseNumber);
	}


