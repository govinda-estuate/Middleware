package com.est.middleware.project.estuate.Wso2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "e_ebs_jira")
public class Jira {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "jira_auto_id")
	    private int jiraSrlId;
	    @Column(name = "issue_id")
	    private int issueId;
	    @Column(name = "connector_id")
	    private String connectorId;
	    @Column(name = "ticket_id")
	    private int ticketId;
	    @Column(name = "organization_id")
	    private String organizationId;
	    
	    public Jira() {
	    }
	    
	    public Jira(final int issueId, final String connectorId, final int ticketId, final String organizationId) {
	        this.issueId = issueId;
	        this.connectorId = connectorId;
	        this.ticketId = ticketId;
	        this.organizationId = organizationId;
	    }
	    
	    public int getJiraSrlId() {
	        return this.jiraSrlId;
	    }
	    
	    public void setJiraSrlId(final int jiraSrlId) {
	        this.jiraSrlId = jiraSrlId;
	    }
	    
	    public int getIssueId() {
	        return this.issueId;
	    }
	    
	    public void setIssueId(final int issueId) {
	        this.issueId = issueId;
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


