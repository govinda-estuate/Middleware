package com.est.middleware.project.estuate.Wso2.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Salesforce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sf_auto_id")
    private int sfSrlId;
    @Column(name = "case_id")
    private String caseId;
    @Column(name = "case_number")
    private String caseNumber;
    @Column(name = "ticket_id")
    private int ticketId;
    @Column(name = "organization_id")
    private String organizationId;
    
    public Salesforce() {
    }
    
    public Salesforce(final String caseId, final String caseNumber, final int ticketId, final String organizationId) {
        this.caseId = caseId;
        this.caseNumber = caseNumber;
        this.ticketId = ticketId;
        this.organizationId = organizationId;
    }
    
    public int getSfSrlId() {
        return this.sfSrlId;
    }
    
    public void setSfSrlId(final int sfSrlId) {
        this.sfSrlId = sfSrlId;
    }
    
    public String getCaseId() {
        return this.caseId;
    }
    
    public void setCaseId(final String caseId) {
        this.caseId = caseId;
    }
    
    public String getCaseNumber() {
        return this.caseNumber;
    }
    
    public void setCaseNumber(final String caseNumber) {
        this.caseNumber = caseNumber;
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
    
    @Override
    public String toString() {
        return "Salesforce [sfSrlId=" + this.sfSrlId + ", caseNumber=" + this.caseNumber + ", ticketId=" + this.ticketId + ", organizationId=" + this.organizationId + "]";
    }
}


