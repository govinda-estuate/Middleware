package com.est.middleware.project.estuate.Wso2.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.est.middleware.project.estuate.Wso2.dao.EsbDao;
import com.est.middleware.project.estuate.Wso2.entity.Connector;
import com.est.middleware.project.estuate.Wso2.entity.ConnectorInfo;
import com.est.middleware.project.estuate.Wso2.entity.Device42;
import com.est.middleware.project.estuate.Wso2.entity.Jira;
import com.est.middleware.project.estuate.Wso2.entity.Organisation;
import com.est.middleware.project.estuate.Wso2.entity.Salesforce;
import com.est.middleware.project.estuate.Wso2.entity.Template;
import com.est.middleware.project.estuate.Wso2.entity.UserDetail;

@Repository
public class EsbDaoImpl implements EsbDao {
	 @Autowired
	    private SessionFactory sessionFactory;
	    
	    public List<Connector> getConnectorsByOrganization(final String orgId) {
	        final Query query = this.sessionFactory.getCurrentSession().createQuery("from Connector c where c.organization_id = :id and c.isActive = :isActive");
	        query.setParameter("id", (Object)orgId);
	        query.setParameter("isActive", (Object)'Y');
	        final List<Connector> connectors = (List<Connector>)query.list();
	        System.out.println(connectors);
	        return connectors;
	    }
	    
	    public Connector getConnector(final String orgId, final String connectorId) {
	        Connector connector = null;
	        final Query query = this.sessionFactory.getCurrentSession().createQuery("from Connector c where c.organization_id = :id and c.connectorId = :connectorId");
	        query.setParameter("id", (Object)orgId);
	        query.setParameter("connectorId", (Object)connectorId);
	        final List<Connector> connectors = (List<Connector>)query.list();
	        if (!connectors.isEmpty()) {
	            connector = connectors.get(0);
	        }
	        return connector;
	    }
	    
	    public Template getTemplateByConnectorId(final String connectorId) {
	        final Query query = this.sessionFactory.getCurrentSession().createQuery("from Template t where t.connectorId = :id");
	        query.setParameter("id", (Object)connectorId);
	        final Template template = (Template)query.getSingleResult();
	        return template;
	    }
	    
	    public List<Jira> getJiraIssuesBasedonTicketId(final int ticketId, final String organizationId) {
	        final Query query = this.sessionFactory.getCurrentSession().createQuery("from Jira j where j.ticketId = :ticketId and j.organizationId = :organizationId");
	        query.setParameter("ticketId", (Object)ticketId);
	        query.setParameter("organizationId", (Object)organizationId);
	        final List<Jira> jiraList = (List<Jira>)query.list();
	        return jiraList;
	    }
	    
	    public void saveJiraInfo(final Jira jira) {
	        int affectedRow = 0;
	        affectedRow = (int)this.sessionFactory.getCurrentSession().save((Object)jira);
	        System.out.println(affectedRow);
	    }
	    
	    public void saveDevice42Info(final Device42 device) {
	        int affectedRow = 0;
	        affectedRow = (int)this.sessionFactory.getCurrentSession().save((Object)device);
	        System.out.println(affectedRow);
	    }
	    
	    public void saveSalesforceInfo(final Salesforce salesforce) {
	        int affectedRow = 0;
	        affectedRow = (int)this.sessionFactory.getCurrentSession().save((Object)salesforce);
	        System.out.println(affectedRow);
	    }
	    
	    public List<Device42> getDevice42NameBasedonTicketId(final int ticketId, final String organizationId) {
	        final Query query = this.sessionFactory.getCurrentSession().createQuery("from Device42 d where d.ticketId = :ticketId and d.organizationId = :organizationId");
	        query.setParameter("ticketId", (Object)ticketId);
	        query.setParameter("organizationId", (Object)organizationId);
	        final List<Device42> deviceList = (List<Device42>)query.list();
	        return deviceList;
	    }
	    
	    public void saveConnector(final Connector connector) {
	        int affectedRow = 0;
	        affectedRow = (int)this.sessionFactory.getCurrentSession().save((Object)connector);
	        System.out.println(affectedRow);
	    }
	    
	    public void saveOrganization(final Organisation organization) {
	        int affectedRow = 0;
	        affectedRow = (int)this.sessionFactory.getCurrentSession().save((Object)organization);
	        System.out.println(affectedRow);
	    }
	    
	    public void setConnectorStatus(final String orgId, final String connectorId, final Character status) {
	        final Query query = this.sessionFactory.getCurrentSession().createQuery("update Connector c set c.isActive = :isActive where c.organization_id = :organizationId and c.connectorId = :connectorId");
	        query.setParameter("organizationId", (Object)orgId);
	        query.setParameter("connectorId", (Object)connectorId);
	        query.setParameter("isActive", (Object)status);
	        final int resut = query.executeUpdate();
	        System.out.println(resut);
	    }
	    
	    public UserDetail validateEmailPassword(final String emailId, final String password) {
	        UserDetail user = null;
	        if (emailId == null || emailId.trim().isEmpty() || password == null || password.trim().isEmpty()) {
	            return user;
	        }
	        final Query query = this.sessionFactory.getCurrentSession().createQuery("from UserDetail where emailId = :emailId and password = :password ");
	        query.setParameter("emailId", (Object)emailId);
	        query.setParameter("password", (Object)password);
	        final List results = query.getResultList();
	        if (!results.isEmpty()) {
	            user = (UserDetail) results.get(0);
	        }
	        return user;
	    }
	    
	    public List<ConnectorInfo> getAllConnectorInfo() {
	        final Query query = this.sessionFactory.getCurrentSession().createQuery("from ConnectorInfo c");
	        final List<ConnectorInfo> connectors = (List<ConnectorInfo>)query.list();
	        return connectors;
	    }
	    
	    public Organisation getOrganizationById(final String organizationId) {
	        Organisation organization = null;
	        final Query query = this.sessionFactory.getCurrentSession().createQuery("from Organization o where o.organizationId = :id");
	        query.setParameter("id", (Object)organizationId);
	        final List<Organisation> organizations = (List<Organisation>)query.list();
	        System.out.println(organizations);
	        if (!organizations.isEmpty()) {
	            organization = organizations.get(0);
	        }
	        return organization;
	    }
	    
	    public List<Salesforce> getSFIssuesBasedonTicketId(final int ticketId, final String organizationId) {
	        final Query query = this.sessionFactory.getCurrentSession().createQuery("from Salesforce s where s.ticketId = :ticketId and s.organizationId = :organizationId");
	        query.setParameter("ticketId", (Object)ticketId);
	        query.setParameter("organizationId", (Object)organizationId);
	        final List<Salesforce> salesforces = (List<Salesforce>)query.list();
	        return salesforces;
	    }
	}


