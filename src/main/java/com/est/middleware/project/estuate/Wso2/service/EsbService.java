package com.est.middleware.project.estuate.Wso2.service;

import java.util.List;

import com.est.middleware.project.estuate.Wso2.entity.Connector;
import com.est.middleware.project.estuate.Wso2.entity.ConnectorInfo;
import com.est.middleware.project.estuate.Wso2.entity.Organisation;
import com.est.middleware.project.estuate.Wso2.entity.Salesforce;
import com.est.middleware.project.estuate.Wso2.entity.UserDetail;
import com.est.middleware.project.estuate.Wso2.model.DefaultRequestBody;
import com.est.middleware.project.estuate.Wso2.model.Device42Dto;
import com.est.middleware.project.estuate.Wso2.model.JiraResponseDto;
import com.est.middleware.project.estuate.Wso2.util.ResponseDto;

public interface EsbService {
List<Connector> getConnectorsByOrganization(final String orgId);
    
    List<ConnectorInfo> getAllConnectorInfo();
    
    UserDetail validateEmailPassword(final String emailId, final String password);
    
    String getTemplateByConnectorId(final String connectorId, final int ticketId, final String organizationId);
    
    ResponseDto saveconnectorsForDomain(final String organizationId, final List<String> connectors);
    
    ResponseDto setConnectorStatus(final String orgId, final String connectorId, final Character status);
    
    Organisation getOrganizationById(final String organizationId);
    
    ResponseDto updateConnectorsForDomain(final String organizationId, final List<String> asList);
    
    JiraResponseDto getJiraIssue(final String id);
    
    Device42Dto getDevice42(final String name);
    
    ResponseDto create(final DefaultRequestBody requestBody);
    
    Salesforce getSFCase(final String caseNumber);
}


