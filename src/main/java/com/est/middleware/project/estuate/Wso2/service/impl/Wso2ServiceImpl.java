package com.est.middleware.project.estuate.Wso2.service.impl;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.est.middleware.project.estuate.Wso2.config.DatabaseConfig;
import com.est.middleware.project.estuate.Wso2.model.CreateJiraJsonObject;
import com.est.middleware.project.estuate.Wso2.model.salesforce.CaseJsonRequest;
import com.est.middleware.project.estuate.Wso2.service.Wso2Service;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import antlr.collections.List;

@Service
@Transactional
public class Wso2ServiceImpl implements Wso2Service {
	 private final Logger log;
	    @Autowired
	    private DatabaseConfig databaseConfig;
	    private static String JIRA_BASIC_AUTH_USERNAME;
	    private static String JIRA_BASIC_AUTH_PASSWORD;
	    private static String D42_BASIC_AUTH_USERNAME;
	    private static String D42_BASIC_AUTH_PASSWORD;
	    private static String SF_GRANT_TYPE_PASSWORD;
	    private static String SF_USERNAME;
	    private static String SF_PASSWORD;
	    private static String SF_CLIENT_ID;
	    private static String SF_CLIENT_SECRET;
	    
	    public Wso2ServiceImpl() {
	        this.log = LoggerFactory.getLogger((Class)this.getClass());
	    }
	    
	    public ResponseEntity<String> getJiraIssue(final String issueId) {
	        try {
	            final RestTemplate restTemplate = new RestTemplate();
	            System.out.println(this.databaseConfig.WSO2_VM_IP_AND_PORT);
	            final String uri = this.databaseConfig.WSO2_VM_IP_AND_PORT + "/getIssue/issue/{id}";
	            final HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_JSON);
	            headers.setAccept((java.util.List<MediaType>)Arrays.asList(MediaType.APPLICATION_JSON));
	            headers.setBasicAuth(this.databaseConfig.WSO2_BASIC_AUTH_NAME, this.databaseConfig.WSO2_BASIC_AUTH_PASSWORD);
	            final HttpEntity request = new HttpEntity((MultiValueMap)headers);
	            ResponseEntity<String> response = null;
	            try {
	                response = (ResponseEntity<String>)restTemplate.exchange(uri, HttpMethod.GET, request, (Class)String.class, new Object[] { issueId });
	            }
	            catch (Exception e2) {
	                return null;
	            }
	            final String body = (String)response.getBody();
	            final JsonObject convertedObject = (JsonObject)new Gson().fromJson(body, (Class)JsonObject.class);
	            return response;
	        }
	        catch (Exception e) {
	            System.out.println("Exception: getJiraIssue");
	            this.log.error("Exception: getJiraIssue : " + e);
	            return null;
	        }
	    }
	    
	    public ResponseEntity<String> createJiraIssue(final CreateJiraJsonObject requestBody) {
	        try {
	            final RestTemplate restTemplate = new RestTemplate();
	            final String uri = this.databaseConfig.WSO2_VM_IP_AND_PORT + "/createIssue";
	            final HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_JSON);
	            headers.setAccept((java.util.List<MediaType>)Arrays.asList(MediaType.APPLICATION_JSON));
	            headers.setBasicAuth(this.databaseConfig.WSO2_BASIC_AUTH_NAME, this.databaseConfig.WSO2_BASIC_AUTH_PASSWORD);
	            final HttpEntity<CreateJiraJsonObject> entity = (HttpEntity<CreateJiraJsonObject>)new HttpEntity((Object)requestBody, (MultiValueMap)headers);
	            System.out.println(entity);
	            final ResponseEntity<String> response = (ResponseEntity<String>)restTemplate.exchange(uri, HttpMethod.POST, (HttpEntity)entity, (Class)String.class, new Object[0]);
	            return response;
	        }
	        catch (Exception e) {
	            this.log.error("Exception: createJiraIssue : " + e);
	            return null;
	        }
	    }
	    
	    public ResponseEntity<String> getDevice42(final String name) {
	        try {
	            final RestTemplate restTemplate = new RestTemplate();
	            final String uri = "https://192.168.0.50/api/1.0/devices/name/{name}";
	            final HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_JSON);
	            headers.setAccept((java.util.List<MediaType>)Arrays.asList(MediaType.APPLICATION_JSON));
	            System.out.println(Wso2ServiceImpl.D42_BASIC_AUTH_USERNAME);
	            headers.setBasicAuth(Wso2ServiceImpl.D42_BASIC_AUTH_USERNAME, Wso2ServiceImpl.D42_BASIC_AUTH_PASSWORD);
	            final HttpEntity request = new HttpEntity((MultiValueMap)headers);
	            final ResponseEntity<String> response = (ResponseEntity<String>)restTemplate.exchange("https://192.168.0.50/api/1.0/devices/name/{name}", HttpMethod.GET, request, (Class)String.class, new Object[] { name });
	            return response;
	        }
	        catch (Exception e) {
	            this.log.error("Exception: getDevice42 : " + e);
	            return null;
	        }
	    }
	    
	    public ResponseEntity<String> getSFCase(final String caseNumber) {
	        try {
	            final RestTemplate restTemplate = new RestTemplate();
	            final String uri = "https://cunning-shark-6tfo6l-dev-ed.my.salesforce.com/services/data/v20.0/query";
	            final HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_JSON);
	            headers.setAccept((java.util.List<MediaType>)Arrays.asList(MediaType.APPLICATION_JSON));
	            headers.setBearerAuth(this.getSFBearerAuthToken());
	            final HttpEntity request = new HttpEntity((MultiValueMap)headers);
	            final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://cunning-shark-6tfo6l-dev-ed.my.salesforce.com/services/data/v20.0/query").queryParam("q", new Object[] { "SELECT Id, Subject, Status FROM Case WHERE CaseNumber='" + caseNumber + "'" });
	            System.out.println(builder.build().toUri());
	            final ResponseEntity<String> response = (ResponseEntity<String>)restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, request, (Class)String.class);
	            final String body = (String)response.getBody();
	            final JsonObject convertedObject = (JsonObject)new Gson().fromJson(body, (Class)JsonObject.class);
	            return response;
	        }
	        catch (Exception e) {
	            this.log.error("Exception: getSFCase : " + e);
	            return null;
	        }
	    }
	    
	    public ResponseEntity<String> createSFCase(final CaseJsonRequest jsonRequest) {
	        try {
	            final RestTemplate restTemplate = new RestTemplate();
	            final String uri = "https://cunning-shark-6tfo6l-dev-ed.my.salesforce.com/services/data/v42.0/sobjects/Case";
	            final HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_JSON);
	            headers.setAccept((java.util.List<MediaType>)Arrays.asList(MediaType.APPLICATION_JSON));
	            headers.setBearerAuth(this.getSFBearerAuthToken());
	            final HttpEntity<CaseJsonRequest> entity = (HttpEntity<CaseJsonRequest>)new HttpEntity((Object)jsonRequest, (MultiValueMap)headers);
	            System.out.println(entity);
	            final ResponseEntity<String> response = (ResponseEntity<String>)restTemplate.exchange("https://cunning-shark-6tfo6l-dev-ed.my.salesforce.com/services/data/v42.0/sobjects/Case", HttpMethod.POST, (HttpEntity)entity, (Class)String.class, new Object[0]);
	            return response;
	        }
	        catch (Exception e) {
	            this.log.error("Exception: getDevice42 : " + e);
	            return null;
	        }
	    }
	    
	    public String getSFBearerAuthToken() {
	        String token = "";
	        try {
	            final RestTemplate restTemplate = new RestTemplate();
	            final String uri = "https://cunning-shark-6tfo6l-dev-ed.my.salesforce.com/services/oauth2/token";
	            final HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_JSON);
	            headers.setAccept((java.util.List<MediaType>)Arrays.asList(MediaType.APPLICATION_JSON));
	            final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://cunning-shark-6tfo6l-dev-ed.my.salesforce.com/services/oauth2/token").queryParam("grant_type", new Object[] { Wso2ServiceImpl.SF_GRANT_TYPE_PASSWORD }).queryParam("username", new Object[] { Wso2ServiceImpl.SF_USERNAME }).queryParam("password", new Object[] { Wso2ServiceImpl.SF_PASSWORD }).queryParam("client_id", new Object[] { Wso2ServiceImpl.SF_CLIENT_ID }).queryParam("client_secret", new Object[] { Wso2ServiceImpl.SF_CLIENT_SECRET });
	            System.out.println(builder.build().toUri());
	            final HttpEntity entity = new HttpEntity((MultiValueMap)headers);
	            System.out.println(entity);
	            final ResponseEntity<String> response = (ResponseEntity<String>)restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, entity, (Class)String.class);
	            if (response != null && response.getStatusCode().is2xxSuccessful()) {
	                final JsonObject object = (JsonObject)new Gson().fromJson((String)response.getBody(), (Class)JsonObject.class);
	                token = object.get("access_token").getAsString();
	            }
	        }
	        catch (Exception e) {
	            this.log.error("Exception: getSFBearerAuthToken : " + e);
	        }
	        return token;
	    }
	    
	    static {
	        Wso2ServiceImpl.JIRA_BASIC_AUTH_USERNAME = "kexagep735@box4mls.com";
	        Wso2ServiceImpl.JIRA_BASIC_AUTH_PASSWORD = "AOxROWVpqJlIEzWy7XbJ0F2C";
	        Wso2ServiceImpl.D42_BASIC_AUTH_USERNAME = "admin";
	        Wso2ServiceImpl.D42_BASIC_AUTH_PASSWORD = "adm!nd42";
	        Wso2ServiceImpl.SF_GRANT_TYPE_PASSWORD = "password";
	        Wso2ServiceImpl.SF_USERNAME = "prashanthdsalesforce@cunning-shark-6tfo6l.com";
	        Wso2ServiceImpl.SF_PASSWORD = "Training@123tc9ybdzvEOl0J5RGY3CTNTN4";
	        Wso2ServiceImpl.SF_CLIENT_ID = "3MVG97quAmFZJfVzVPCO4AAl7IHVvEDcTSpoh0lmlxSn8nhbjp9l_LEg4nPB5qoaprb5buXnqBsuC6lxRicrp";
	        Wso2ServiceImpl.SF_CLIENT_SECRET = "7BE0078BBB3D20D20F7DACFFE0638F154F6C8E16AF31C681E61A1F4BA8E44FFA";
	    }
	}

	 