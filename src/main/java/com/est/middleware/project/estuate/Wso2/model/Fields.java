package com.est.middleware.project.estuate.Wso2.model;

public class Fields {
	 private Project project;
	    private String summary;
	    private IssueType issuetype;
	    
	    public Fields(final Project project, final String summary, final IssueType issuetype) {
	        this.project = project;
	        this.summary = summary;
	        this.issuetype = issuetype;
	    }
	    
	    public void setProject(final Project project) {
	        this.project = project;
	    }
	    
	    public Project getProject() {
	        return this.project;
	    }
	    
	    public void setSummary(final String summary) {
	        this.summary = summary;
	    }
	    
	    public String getSummary() {
	        return this.summary;
	    }
	    
	    public void setIssuetype(final IssueType issuetype) {
	        this.issuetype = issuetype;
	    }
	    
	    public IssueType getIssuetype() {
	        return this.issuetype;
	    }
	}


