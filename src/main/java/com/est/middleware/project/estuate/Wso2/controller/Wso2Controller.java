package com.est.middleware.project.estuate.Wso2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.est.middleware.project.estuate.Wso2.entity.Salesforce;
import com.est.middleware.project.estuate.Wso2.model.DefaultRequestBody;
import com.est.middleware.project.estuate.Wso2.model.Device42Dto;
import com.est.middleware.project.estuate.Wso2.model.JiraResponseDto;
import com.est.middleware.project.estuate.Wso2.service.EsbService;
import com.est.middleware.project.estuate.Wso2.util.ResponseDto;

@RestController
@RequestMapping({"/esb/wso2"})
public class Wso2Controller {
	 
	    @Autowired
	    private EsbService esbService;
	    
	    @PostMapping({ "/create" })
	    private ResponseDto create(@RequestBody final DefaultRequestBody requestBody) {
	        return this.esbService.create(requestBody);
	    }
	    
	    @GetMapping({ "/getIssue/{id}" })
	    private JiraResponseDto getIssue(@PathVariable final String id) {
	        return this.esbService.getJiraIssue(id);
	    }
	    
	    @GetMapping({ "/getDevice42/{name}" })
	    private Device42Dto getDevice42(@PathVariable final String name) {
	        return this.esbService.getDevice42(name);
	    }
	    
	    @GetMapping({ "/getSFCase/{caseNumber}" })
	    private Salesforce getSFCase(@PathVariable final String caseNumber) {
	        return this.esbService.getSFCase(caseNumber);
	    }
	    
	    @GetMapping({ "/testw" })
	    public String test() {
	        return "welcome";
	    }
	}


