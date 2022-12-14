package com.est.middleware.project.estuate.Wso2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.est.middleware.project.estuate.Wso2.entity.Connector;
import com.est.middleware.project.estuate.Wso2.entity.Organisation;
import com.est.middleware.project.estuate.Wso2.service.EsbService;
import com.est.middleware.project.estuate.Wso2.util.ResponseDto;

@RestController
@RequestMapping({"/esb"})
public class EsbController {
	@Autowired
    private EsbService esbService;
    
    @GetMapping({ "/getConnectorsByOrganization/{orgId}" })
    public List<Connector> getConnectorsByOrganization(@PathVariable final String orgId) {
        return (List<Connector>)this.esbService.getConnectorsByOrganization(orgId);
    }
    
    @GetMapping({ "/getTemplateByConnectorId/{connectorId}/{ticketId}/{organizationId}" })
    @ResponseBody
    public String getTemplateByConnectorId(@PathVariable final String connectorId, @PathVariable final int ticketId, @PathVariable final String organizationId) {
        final String template = this.esbService.getTemplateByConnectorId(connectorId, ticketId, organizationId);
        return template;
    }
    
    @GetMapping({ "/setConnectorStatus/{orgId}/{connectorId}/{status}" })
    public ResponseDto setConnectorStatus(@PathVariable final String orgId, @PathVariable final String connectorId, @PathVariable final Character status) {
        return this.esbService.setConnectorStatus(orgId, connectorId, status);
    }
    
    @GetMapping({ "/getOrganizationByConnectors/{orgId}" })
    public Organisation getOrganizationById(@PathVariable final String orgId) {
        return this.esbService.getOrganizationById(orgId);
    }
    
    @GetMapping({ "/test" })
    public String test() {
        return "welcome";
    }
}