package com.est.middleware.project.estuate.Wso2.service.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.est.middleware.project.estuate.Wso2.dao.EsbDao;
import com.est.middleware.project.estuate.Wso2.entity.Connector;
import com.est.middleware.project.estuate.Wso2.entity.ConnectorInfo;
import com.est.middleware.project.estuate.Wso2.entity.Device42;
import com.est.middleware.project.estuate.Wso2.entity.Jira;
import com.est.middleware.project.estuate.Wso2.entity.Organisation;
import com.est.middleware.project.estuate.Wso2.entity.Salesforce;
import com.est.middleware.project.estuate.Wso2.entity.UserDetail;
import com.est.middleware.project.estuate.Wso2.model.CreateJiraJsonObject;
import com.est.middleware.project.estuate.Wso2.model.DefaultRequestBody;
import com.est.middleware.project.estuate.Wso2.model.Device42Dto;
import com.est.middleware.project.estuate.Wso2.model.Fields;
import com.est.middleware.project.estuate.Wso2.model.IssueType;
import com.est.middleware.project.estuate.Wso2.model.JiraResponseDto;
import com.est.middleware.project.estuate.Wso2.model.Project;
import com.est.middleware.project.estuate.Wso2.model.RequestFieldValues;
import com.est.middleware.project.estuate.Wso2.service.EsbService;
import com.est.middleware.project.estuate.Wso2.service.TemplateService;
import com.est.middleware.project.estuate.Wso2.service.Wso2Service;
import com.est.middleware.project.estuate.Wso2.util.RequestParamEnum;
import com.est.middleware.project.estuate.Wso2.util.ResponseDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
@Transactional
public class EsbServiceImpl implements EsbService{
	private final Logger log;
    @Autowired
    private EsbDao esbDao;
    @Autowired
    private Wso2Service wso2Service;
    @Autowired
    private TemplateService templateService;
    
    public EsbServiceImpl() {
        this.log = LoggerFactory.getLogger((Class)this.getClass());
    }
    
    public List<Connector> getConnectorsByOrganization(final String orgId) {
        return (List<Connector>)this.esbDao.getConnectorsByOrganization(orgId);
    }
    
    public String getTemplateByConnectorId(final String connectorId, final int ticketId, final String organizationId) {
        String template = "";
        if (connectorId != "") {
            if (connectorId.equalsIgnoreCase("jira") && ticketId != 0) {
                template = this.templateService.getJiraTemplate(connectorId, ticketId, organizationId);
            }
            else if (connectorId.equalsIgnoreCase("d42") && ticketId != 0) {
                template = this.templateService.getDevice42Template(connectorId, ticketId, organizationId);
            }
            else if (connectorId.equalsIgnoreCase("salesforce") && ticketId != 0) {
                template = this.templateService.getsalesforceTemplate(connectorId, ticketId, organizationId);
            }
            else {
                template = this.templateService.getCommonTemplate();
            }
        }
        return template;
    }
    
    public ResponseDto create(final DefaultRequestBody defaultRequest) {
        ResponseDto responseDto = null;
        if (defaultRequest != null) {
            if (defaultRequest.getAction().equalsIgnoreCase(RequestParamEnum.CREATE_JIRA_ACTION.getParams())) {
                responseDto = this.createJiraIssue(defaultRequest);
            }
            if (defaultRequest.getAction().equalsIgnoreCase(RequestParamEnum.LINK_DEVICE_ACTION.getParams())) {
                responseDto = this.linkDevice42(defaultRequest);
            }
            if (defaultRequest.getAction().equalsIgnoreCase(RequestParamEnum.LINK_ISSUE_ACTION.getParams())) {
                responseDto = this.linkJiraIssue(defaultRequest);
            }
            if (defaultRequest.getAction().equalsIgnoreCase(RequestParamEnum.GET_SF_CASE_ACTION.getParams())) {
                responseDto = this.getSFCaseIssue(defaultRequest);
            }
        }
        return responseDto;
    }
    
    private ResponseDto getSFCaseIssue(final DefaultRequestBody defaultRequest) {
        String caseNumber = "";
        final ResponseDto responseDto = new ResponseDto();
        if (defaultRequest != null) {
            final List<RequestFieldValues> requestFields = (List<RequestFieldValues>)defaultRequest.getFields();
            if (requestFields != null && !requestFields.isEmpty()) {
                for (final RequestFieldValues requestFieldValue : requestFields) {
                    if (requestFieldValue.getName().equals(RequestParamEnum.SF_CASE_NUMBER.getParams())) {
                        caseNumber = requestFieldValue.getValue();
                    }
                }
            }
            final ResponseEntity<String> response = (ResponseEntity<String>)this.wso2Service.getSFCase(caseNumber);
            if (response != null && response.getStatusCode().is2xxSuccessful()) {
                final JsonObject object = (JsonObject)new Gson().fromJson((String)response.getBody(), (Class)JsonObject.class);
                final Salesforce salesforce = new Salesforce();
                if (object.get("totalSize").getAsInt() > 0) {
                    salesforce.setCaseId(object.get("records").getAsJsonArray().get(0).getAsJsonObject().get("Id").getAsString());
                    salesforce.setCaseNumber(caseNumber);
                    salesforce.setOrganizationId(defaultRequest.getDomain());
                    salesforce.setTicketId(defaultRequest.getTicketId());
                    this.esbDao.saveSalesforceInfo(salesforce);
                }
                else {
                    System.out.println(caseNumber + " records not found");
                }
                responseDto.setStatusCode(1);
                responseDto.setMessage("SUCCESS");
            }
            else {
                responseDto.setStatusCode(0);
                responseDto.setMessage("FAIL");
            }
        }
        return responseDto;
    }
    
    public JiraResponseDto getJiraIssue(final String id) {
        final ResponseEntity<String> response = (ResponseEntity<String>)this.wso2Service.getJiraIssue(id);
        final JiraResponseDto responseDto = new JiraResponseDto();
        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            final JsonObject object = (JsonObject)new Gson().fromJson((String)response.getBody(), (Class)JsonObject.class);
            responseDto.setId(object.get("id").getAsString());
            responseDto.setKey(object.get("key").getAsString());
            responseDto.setStatus(1);
        }
        else {
            responseDto.setStatus(0);
        }
        return responseDto;
    }
    
    private ResponseDto linkJiraIssue(final DefaultRequestBody defaultRequest) {
        String issueId = "";
        final ResponseDto responseDto = new ResponseDto();
        final List<RequestFieldValues> requestFields = (List<RequestFieldValues>)defaultRequest.getFields();
        if (requestFields != null && !requestFields.isEmpty()) {
            for (final RequestFieldValues requestFieldValues : requestFields) {
                if (requestFieldValues.getName().equals(RequestParamEnum.ISSUE_ID.getParams())) {
                    issueId = requestFieldValues.getValue();
                }
            }
            final ResponseEntity<String> response = (ResponseEntity<String>)this.wso2Service.getJiraIssue(issueId);
            if (response != null) {
                if (response.getStatusCode().is2xxSuccessful()) {
                    final JsonObject object = (JsonObject)new Gson().fromJson((String)response.getBody(), (Class)JsonObject.class);
                    final Jira jira = new Jira(object.get("id").getAsInt(), defaultRequest.getHost(), defaultRequest.getTicketId(), defaultRequest.getDomain());
                    this.esbDao.saveJiraInfo(jira);
                    responseDto.setStatusCode(1);
                    responseDto.setMessage("SUCCESS");
                    responseDto.setErrorCode(0);
                }
                else {
                    responseDto.setStatusCode(0);
                    responseDto.setMessage("FAIL");
                }
            }
            else {
                responseDto.setStatusCode(0);
                responseDto.setMessage("FAIL");
            }
        }
        return responseDto;
    }
    
    private ResponseDto createJiraIssue(final DefaultRequestBody defaultRequest) {
        Project project = null;
        IssueType issueType = null;
        Fields fields = null;
        CreateJiraJsonObject requestBody = null;
        final ResponseDto responseDto = new ResponseDto();
        String summary = "";
        final List<RequestFieldValues> requestFields = (List<RequestFieldValues>)defaultRequest.getFields();
        if (requestFields != null && !requestFields.isEmpty()) {
            for (final RequestFieldValues requestFieldValues : requestFields) {
                if (requestFieldValues.getName().equals(RequestParamEnum.KEY.getParams())) {
                    project = new Project(requestFieldValues.getValue());
                }
                if (requestFieldValues.getName().equals(RequestParamEnum.SUMMARY.getParams())) {
                    summary = requestFieldValues.getValue();
                }
                if (requestFieldValues.getName().equals(RequestParamEnum.NAME.getParams())) {
                    issueType = new IssueType(requestFieldValues.getValue());
                }
            }
            fields = new Fields(project, summary, issueType);
            requestBody = new CreateJiraJsonObject(fields);
            final ResponseEntity<String> response = (ResponseEntity<String>)this.wso2Service.createJiraIssue(requestBody);
            if (response != null && response.getStatusCode().is2xxSuccessful()) {
                final JsonObject object = (JsonObject)new Gson().fromJson((String)response.getBody(), (Class)JsonObject.class);
                responseDto.setStatusCode(1);
                responseDto.setMessage("SUCCESS");
                responseDto.setErrorCode(0);
                final Jira jira = new Jira(object.get("id").getAsInt(), defaultRequest.getHost(), defaultRequest.getTicketId(), defaultRequest.getDomain());
                this.esbDao.saveJiraInfo(jira);
            }
        }
        else {
            responseDto.setStatusCode(0);
        }
        return responseDto;
    }
    
    public Device42Dto getDevice42(final String name) {
        final ResponseEntity<String> response = (ResponseEntity<String>)this.wso2Service.getDevice42(name);
        final Device42Dto device42Dto = new Device42Dto();
        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            final JsonObject object = (JsonObject)new Gson().fromJson((String)response.getBody(), (Class)JsonObject.class);
            device42Dto.setAsset_number(object.get("asset_no").getAsString());
            device42Dto.setIs_it_virtual_host(object.get("is_it_virtual_host").getAsString());
            device42Dto.setLast_updated(object.get("last_updated").getAsString());
            device42Dto.setName(object.get("name").getAsString());
            device42Dto.setSerial_number(object.get("serial_no").getAsInt());
            device42Dto.setService_level(object.get("service_level").getAsString());
            device42Dto.setType(object.get("type").getAsString());
            System.out.println(object.get("virtual_host_name").isJsonNull());
            if (!object.get("virtual_host_name").isJsonNull()) {
                device42Dto.setVirtual_host_name(object.get("virtual_host_name").getAsString());
            }
            device42Dto.setResponseStatus(1);
        }
        else {
            device42Dto.setResponseStatus(0);
        }
        return device42Dto;
    }
    
    private ResponseDto linkDevice42(final DefaultRequestBody requestBody) {
        String deviceName = "";
        final ResponseDto responseDto = new ResponseDto();
        if (requestBody != null) {
            final List<RequestFieldValues> requestFields = (List<RequestFieldValues>)requestBody.getFields();
            if (requestFields != null && !requestFields.isEmpty()) {
                for (final RequestFieldValues requestFieldValue : requestFields) {
                    if (requestFieldValue.getName().equals(RequestParamEnum.DEVICE_NAME.getParams())) {
                        deviceName = requestFieldValue.getValue();
                    }
                }
            }
            final ResponseEntity<String> response = (ResponseEntity<String>)this.wso2Service.getDevice42(deviceName);
            if (response != null && response.getStatusCode().is2xxSuccessful()) {
                final JsonObject object = (JsonObject)new Gson().fromJson((String)response.getBody(), (Class)JsonObject.class);
                final Device42 device42 = new Device42();
                device42.setConnectorId(requestBody.getConnector());
                device42.setTicketId(requestBody.getTicketId());
                device42.setDeviceName(object.get("name").getAsString());
                device42.setOrganizationId(requestBody.getDomain());
                this.esbDao.saveDevice42Info(device42);
                responseDto.setStatusCode(1);
                responseDto.setMessage("SUCCESS");
            }
            else {
                responseDto.setStatusCode(0);
                responseDto.setMessage("FAIL");
            }
        }
        return responseDto;
    }
    
    public ResponseDto saveconnectorsForDomain(final String organizationId, final List<String> connectors) {
        final ResponseDto responseDto = new ResponseDto();
        try {
            final Organisation organization = this.esbDao.getOrganizationById(organizationId);
            if (organization == null) {
                this.esbDao.saveOrganization(new Organisation(organizationId, organizationId));
                if (!CollectionUtils.isEmpty((Collection)connectors)) {
                    for (final String conn : connectors) {
                        final Connector connector = new Connector();
                        connector.setConnectorId(conn);
                        if (conn.equals("d42")) {
                            connector.setConnectorName("Device42");
                        }
                        else {
                            final String connName = conn.substring(0, 1).toUpperCase() + conn.substring(1);
                            connector.setConnectorName(connName);
                        }
                        connector.setIsActive(Character.valueOf('Y'));
                        connector.setOrganization_id(organizationId);
                        this.esbDao.saveConnector(connector);
                    }
                    responseDto.setStatusCode(1);
                    responseDto.setMessage("Connectors for the domain " + organizationId + " saved successfully");
                }
            }
            else {
                this.log.info("No organization found " + organizationId);
                responseDto.setStatusCode(0);
                responseDto.setMessage("Organization " + organizationId + " not found in the database");
            }
        }
        catch (Exception e) {
            this.log.info("Exception occured saveconnectorsForDomain method : " + e);
            responseDto.setStatusCode(0);
            responseDto.setMessage("Faied to save connectors : " + e.getMessage());
        }
        return responseDto;
    }
    
    public ResponseDto updateConnectorsForDomain(final String organizationId, final List<String> newConnList) {
        final ResponseDto responseDto = new ResponseDto();
        try {
            final List<Connector> existingConnList = (List<Connector>)this.esbDao.getConnectorsByOrganization(organizationId);
            if (!CollectionUtils.isEmpty((Collection)existingConnList) && CollectionUtils.isEmpty((Collection)newConnList)) {
                for (final Connector connector : existingConnList) {
                    this.esbDao.setConnectorStatus(organizationId, connector.getConnectorId(), Character.valueOf('N'));
                }
            }
            else if (CollectionUtils.isEmpty((Collection)existingConnList) && !CollectionUtils.isEmpty((Collection)newConnList)) {
                for (final String connectorId : newConnList) {
                    final Connector conn = this.esbDao.getConnector(organizationId, connectorId);
                    if (conn != null) {
                        this.esbDao.setConnectorStatus(organizationId, conn.getConnectorId(), Character.valueOf('Y'));
                    }
                    else {
                        final Connector connector2 = new Connector();
                        connector2.setConnectorId(connectorId);
                        if (connectorId.equals("d42")) {
                            connector2.setConnectorName("Device42");
                        }
                        else {
                            final String connName = connectorId.substring(0, 1).toUpperCase() + connectorId.substring(1);
                            connector2.setConnectorName(connName);
                        }
                        connector2.setIsActive(Character.valueOf('Y'));
                        connector2.setOrganization_id(organizationId);
                        this.esbDao.saveConnector(connector2);
                    }
                }
            }
            else if (!CollectionUtils.isEmpty((Collection)existingConnList) && !CollectionUtils.isEmpty((Collection)newConnList)) {
                final List<String> tempList = new LinkedList<String>(newConnList);
                for (final Connector connector3 : existingConnList) {
                    if (newConnList.contains(connector3.getConnectorId())) {
                        tempList.remove(connector3.getConnectorId());
                    }
                    else {
                        this.esbDao.setConnectorStatus(organizationId, connector3.getConnectorId(), Character.valueOf('N'));
                    }
                }
                for (final String connectorId2 : tempList) {
                    final Connector conn2 = this.esbDao.getConnector(organizationId, connectorId2);
                    if (conn2 != null) {
                        this.esbDao.setConnectorStatus(organizationId, conn2.getConnectorId(), Character.valueOf('Y'));
                    }
                    else {
                        final Connector connector4 = new Connector();
                        connector4.setConnectorId(connectorId2);
                        if (connectorId2.equals("d42")) {
                            connector4.setConnectorName("Device42");
                        }
                        else {
                            final String connName2 = connectorId2.substring(0, 1).toUpperCase() + connectorId2.substring(1);
                            connector4.setConnectorName(connName2);
                        }
                        connector4.setIsActive(Character.valueOf('Y'));
                        connector4.setOrganization_id(organizationId);
                        this.esbDao.saveConnector(connector4);
                    }
                }
            }
            else {
                responseDto.setMessage("Nothing to update");
            }
            responseDto.setStatusCode(1);
            responseDto.setMessage("Connectors for the domain " + organizationId + " updated successfully");
        }
        catch (Exception e) {
            this.log.info("Exception occured updateConnectorsForDomain method : " + e);
            responseDto.setStatusCode(0);
            responseDto.setMessage("Faied to update connectors : " + e.getMessage());
        }
        return responseDto;
    }
    
    public ResponseDto setConnectorStatus(final String orgId, final String connectorId, final Character status) {
        final ResponseDto responseDto = new ResponseDto();
        try {
            this.esbDao.setConnectorStatus(orgId, connectorId, status);
            responseDto.setStatusCode(1);
            responseDto.setMessage("Successfully changed the connector status");
        }
        catch (Exception e) {
            this.log.info("Exception occured setConnectorStatus method : " + e);
            responseDto.setStatusCode(0);
            responseDto.setMessage("Failed to set connector status : " + e.getMessage());
        }
        return responseDto;
    }
    
    public UserDetail validateEmailPassword(final String emailId, final String password) {
        final UserDetail user = this.esbDao.validateEmailPassword(emailId, password);
        return user;
    }
    
    public List<ConnectorInfo> getAllConnectorInfo() {
        return (List<ConnectorInfo>)this.esbDao.getAllConnectorInfo();
    }
    
    public Organisation getOrganizationById(final String organizationId) {
        return this.esbDao.getOrganizationById(organizationId);
    }
    
    public Salesforce getSFCase(final String caseNumber) {
        final ResponseEntity<String> response = (ResponseEntity<String>)this.wso2Service.getSFCase(caseNumber);
        final Salesforce salesforce = new Salesforce();
        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            final JsonObject object = (JsonObject)new Gson().fromJson((String)response.getBody(), (Class)JsonObject.class);
            if (object.get("records").getAsInt() > 0) {
                salesforce.setCaseId(object.get("records").getAsJsonArray().get(0).getAsJsonObject().get("Id").getAsString());
                salesforce.setCaseNumber(caseNumber);
            }
            else {
                System.out.println("No records found");
            }
        }
        return salesforce;
    }
}	  