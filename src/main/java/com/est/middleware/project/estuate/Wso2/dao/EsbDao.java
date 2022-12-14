package com.est.middleware.project.estuate.Wso2.dao;

import java.util.List;

import com.est.middleware.project.estuate.Wso2.entity.Connector;
import com.est.middleware.project.estuate.Wso2.entity.ConnectorInfo;
import com.est.middleware.project.estuate.Wso2.entity.Device42;
import com.est.middleware.project.estuate.Wso2.entity.Jira;
import com.est.middleware.project.estuate.Wso2.entity.Organisation;
import com.est.middleware.project.estuate.Wso2.entity.Salesforce;
import com.est.middleware.project.estuate.Wso2.entity.Template;
import com.est.middleware.project.estuate.Wso2.entity.UserDetail;

public interface EsbDao {
List<Connector> getConnectorsByOrganization(final String orgId);
    
    Connector getConnector(final String orgId, final String connectorId);
    
    List<ConnectorInfo> getAllConnectorInfo();
    
    UserDetail validateEmailPassword(final String emailId, final String password);
    
    Template getTemplateByConnectorId(final String connectorId);
    
    List<Jira> getJiraIssuesBasedonTicketId(final int ticketId, final String organizationId);
    
    void saveJiraInfo(final Jira jira);
    
    void saveDevice42Info(final Device42 device);
    
    void saveConnector(final Connector connector);
    
    void saveOrganization(final Organisation organization);
    
    List<Device42> getDevice42NameBasedonTicketId(final int ticketId, final String organizationId);
    
    Organisation getOrganizationById(final String organizationId);
    
    void setConnectorStatus(final String orgId, final String connectorId, final Character status);
    
    void saveSalesforceInfo(final Salesforce salesforce);
    
    List<Salesforce> getSFIssuesBasedonTicketId(final int ticketId, final String organizationId);
}


