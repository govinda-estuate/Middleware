package com.est.middleware.project.estuate.Wso2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.est.middleware.project.estuate.Wso2.dao.EsbDao;
import com.est.middleware.project.estuate.Wso2.entity.Device42;
import com.est.middleware.project.estuate.Wso2.entity.Jira;
import com.est.middleware.project.estuate.Wso2.entity.Salesforce;
import com.est.middleware.project.estuate.Wso2.entity.Template;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TemplateService {
	 @Autowired
	    private EsbDao esbDao;
	    @Autowired
	    private Wso2Service wso2Service;
	    
	    public String getJiraTemplate(final String connectorId, final int ticketId, final String organizationId) {
	        String staticTemplateReplaced = "";
	        final List<Jira> jiraIssues = (List<Jira>)this.esbDao.getJiraIssuesBasedonTicketId(ticketId, organizationId);
	        if (jiraIssues.isEmpty()) {
	            final Template template = this.esbDao.getTemplateByConnectorId(connectorId);
	            final String staticTemplate = staticTemplateReplaced = template.getStaticTemplate();
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{issueId}}", "");
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{key}}", "");
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{Type}}", "");
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{summary}}", "");
	        }
	        else {
	            final ResponseEntity<String> response = (ResponseEntity<String>)this.wso2Service.getJiraIssue(String.valueOf(jiraIssues.get(0).getIssueId()));
	            if (response != null && response.getStatusCode().is2xxSuccessful()) {
	                final JsonObject responseBody = (JsonObject)new Gson().fromJson((String)response.getBody(), (Class)JsonObject.class);
	                System.out.println(responseBody.get("fields").getAsJsonObject().get("summary").getAsString());
	                final Template template2 = this.esbDao.getTemplateByConnectorId(connectorId);
	                final String staticTemplate2 = staticTemplateReplaced = template2.getStaticTemplate();
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{issueId}}", responseBody.get("id").getAsString());
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{key}}", responseBody.get("key").getAsString());
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{Type}}", "Task");
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{summary}}", responseBody.get("fields").getAsJsonObject().get("summary").getAsString());
	            }
	        }
	        return staticTemplateReplaced;
	    }
	    
	    public String getDevice42Template(final String connectorId, final int ticketId, final String organizationId) {
	        String staticTemplateReplaced = "";
	        final List<Device42> device42List = (List<Device42>)this.esbDao.getDevice42NameBasedonTicketId(ticketId, organizationId);
	        if (device42List.isEmpty()) {
	            final Template template = this.esbDao.getTemplateByConnectorId(connectorId);
	            final String staticTemplate = staticTemplateReplaced = template.getStaticTemplate();
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{assetNo}}", "");
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{serialNo}}", "");
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{type}}", "");
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{name}}", "");
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{lastUpdated}}", "");
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{isVirtualHost}}", "");
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{serviceLevel}}", "");
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{virtualHost}}", "");
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{summary}}", "");
	        }
	        else {
	            final ResponseEntity<String> response = (ResponseEntity<String>)this.wso2Service.getDevice42(device42List.get(0).getDeviceName());
	            if (response != null && response.getStatusCode().is2xxSuccessful()) {
	                final JsonObject responseBody = (JsonObject)new Gson().fromJson((String)response.getBody(), (Class)JsonObject.class);
	                final Template template2 = this.esbDao.getTemplateByConnectorId(connectorId);
	                final String staticTemplate2 = staticTemplateReplaced = template2.getStaticTemplate();
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{assetNo}}", responseBody.get("asset_no").getAsString());
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{serialNo}}", responseBody.get("serial_no").getAsString());
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{type}}", responseBody.get("type").getAsString());
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{name}}", responseBody.get("name").getAsString());
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{lastUpdated}}", responseBody.get("last_updated").getAsString());
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{isVirtualHost}}", responseBody.get("is_it_virtual_host").getAsString());
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{serviceLevel}}", responseBody.get("service_level").getAsString());
	                if (!responseBody.get("virtual_host_name").isJsonNull()) {
	                    staticTemplateReplaced = staticTemplateReplaced.replace("{{virtualHost}}", responseBody.get("virtual_host_name").getAsString());
	                }
	                else {
	                    staticTemplateReplaced = staticTemplateReplaced.replace("{{virtualHost}}", "NA");
	                }
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{summary}}", "NA");
	            }
	        }
	        return staticTemplateReplaced;
	    }
	    
	    public String getCommonTemplate() {
	        return "<div class='loader'></div>";
	    }
	    
	    public String getsalesforceTemplate(final String connectorId, final int ticketId, final String organizationId) {
	        String staticTemplateReplaced = "";
	        final List<Salesforce> salesforces = (List<Salesforce>)this.esbDao.getSFIssuesBasedonTicketId(ticketId, organizationId);
	        if (salesforces.isEmpty()) {
	            final Template template = this.esbDao.getTemplateByConnectorId(connectorId);
	            final String staticTemplate = staticTemplateReplaced = template.getStaticTemplate();
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{caseNo}}", "");
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{subject}}", "");
	            staticTemplateReplaced = staticTemplateReplaced.replace("{{status}}", "");
	        }
	        else {
	            final ResponseEntity<String> response = (ResponseEntity<String>)this.wso2Service.getSFCase(String.valueOf(salesforces.get(0).getCaseNumber()));
	            if (response != null && response.getStatusCode().is2xxSuccessful()) {
	                final JsonObject responseBody = (JsonObject)new Gson().fromJson((String)response.getBody(), (Class)JsonObject.class);
	                final Template template2 = this.esbDao.getTemplateByConnectorId(connectorId);
	                final String staticTemplate2 = staticTemplateReplaced = template2.getStaticTemplate();
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{caseNo}}", salesforces.get(0).getCaseNumber());
	                if (!responseBody.get("records").getAsJsonArray().get(0).getAsJsonObject().get("Subject").isJsonNull()) {
	                    staticTemplateReplaced = staticTemplateReplaced.replace("{{subject}}", responseBody.get("records").getAsJsonArray().get(0).getAsJsonObject().get("Subject").getAsString());
	                }
	                else {
	                    staticTemplateReplaced = staticTemplateReplaced.replace("{{subject}}", "NA");
	                }
	                staticTemplateReplaced = staticTemplateReplaced.replace("{{status}}", responseBody.get("records").getAsJsonArray().get(0).getAsJsonObject().get("Status").getAsString());
	            }
	        }
	        return staticTemplateReplaced;
	    }
	}


