package com.est.middleware.project.estuate.Wso2.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.est.middleware.project.estuate.Wso2.entity.Connector;
import com.est.middleware.project.estuate.Wso2.entity.ConnectorInfo;
import com.est.middleware.project.estuate.Wso2.entity.Organisation;
import com.est.middleware.project.estuate.Wso2.entity.UserDetail;
import com.est.middleware.project.estuate.Wso2.service.EsbService;
import com.est.middleware.project.estuate.Wso2.util.ResponseDto;

@Controller
public class UIController {
	private final Logger log;
    @Autowired
    private EsbService esbService;
    
    public UIController() {
        this.log = LoggerFactory.getLogger((Class)this.getClass());
    }
    
    @RequestMapping(value = { "/" }, method = { RequestMethod.GET })
    public String login() {
        this.log.info("Login methd");
        return "index";
    }
    
    @RequestMapping(value = { "/validateLogin" }, method = { RequestMethod.POST })
    public String loginValidate(@RequestParam("emailId") final String emailId, @RequestParam("password") final String password, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model, final RedirectAttributes redir) {
        this.log.info("start executing loginValidate  method");
        UserDetail user = null;
        try {
            if (emailId != null && !emailId.trim().isEmpty() && password != null && !password.trim().isEmpty()) {
                user = this.esbService.validateEmailPassword(emailId, password);
                if (user == null) {
                    System.err.println("errrerwef");
                    this.log.error("Invalid credentials");
                    return "redirect:/";
                }
                this.log.info("Logged in succesfully");
                final List<ConnectorInfo> connectors = (List<ConnectorInfo>)this.esbService.getAllConnectorInfo();
                model.addAttribute("connectors", (Object)connectors);
                return "connector_admin";
            }
        }
        catch (Exception e) {
            this.log.error("Exception : " + e);
        }
        return "redirect:/";
    }
    
    @PostMapping({ "/saveconnectorsForDomain" })
    public String saveconnectorsForDomain(final String organizationId, final String[] connectors, final ModelMap modelMap) {
        final ResponseDto responseDto = this.esbService.saveconnectorsForDomain(organizationId, (List)Arrays.asList(connectors));
        final List<ConnectorInfo> connectorsist = (List<ConnectorInfo>)this.esbService.getAllConnectorInfo();
        modelMap.addAttribute("connectors", (Object)connectorsist);
        if (responseDto.getStatusCode() != 0) {
            modelMap.addAttribute("message", (Object)"Connectors added succesfully!!");
        }
        else {
            modelMap.addAttribute("message", (Object)"Failed to add Connectors!!");
        }
        return "connector_admin";
    }
    
    @PostMapping({ "/updateConnectorsForDomain" })
    public String updateConnectorsForDomain(final String organizationId, final String[] connectors, final ModelMap modelMap) {
        final List<String> tempList = new LinkedList<String>();
        if (connectors != null) {
            tempList.addAll(Arrays.asList(connectors));
        }
        final ResponseDto responseDto = this.esbService.updateConnectorsForDomain(organizationId, (List)tempList);
        final List<ConnectorInfo> connectorsist = (List<ConnectorInfo>)this.esbService.getAllConnectorInfo();
        modelMap.addAttribute("connectors", (Object)connectorsist);
        if (responseDto.getStatusCode() != 0) {
            final List<Connector> domainConnectors = (List<Connector>)this.esbService.getConnectorsByOrganization(organizationId);
            modelMap.addAttribute("domainConnectors", (Object)domainConnectors);
            modelMap.addAttribute("organizationId", (Object)organizationId);
            modelMap.addAttribute("searchmessage", (Object)"Connectors updated succesfully!!");
        }
        else {
            modelMap.addAttribute("searchmessage", (Object)"Failed to update Connectors!!");
        }
        return "modify_admin";
    }
    
    @RequestMapping(value = { "/getConnectorsForDomain" }, method = { RequestMethod.GET })
    public String getOrganizationById(final String organizationId, final ModelMap modelMap) {
        final Organisation organization = this.esbService.getOrganizationById(organizationId);
        if (organization != null) {
            final List<Connector> domainConnectors = (List<Connector>)this.esbService.getConnectorsByOrganization(organizationId);
            modelMap.addAttribute("domainConnectors", (Object)domainConnectors);
        }
        else {
            modelMap.addAttribute("searchmessage", (Object)("Domain " + organizationId + " does not exists!!"));
        }
        final List<ConnectorInfo> connectors = (List<ConnectorInfo>)this.esbService.getAllConnectorInfo();
        modelMap.addAttribute("connectors", (Object)connectors);
        modelMap.addAttribute("organizationId", (Object)organizationId);
        return "modify_admin";
    }
    
    @RequestMapping(value = { "/logout" }, method = { RequestMethod.GET })
    public String logout(final HttpServletRequest request) {
        try {
            request.getSession().invalidate();
            return "redirect:/";
        }
        catch (Exception e) {
            this.log.error("Exception : " + e);
            return "redirect:/";
        }
    }
}

