package com.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.domain.Organization;
import com.work.domain.dto.OrganizationDTO;
import com.work.service.OrganizationService;

@RequestMapping("/organizations")
@RestController
public class OrganizationController {

	@Autowired 
	private OrganizationService orgService;
	
	@RequestMapping(value = "/getOrganizations", method = RequestMethod.GET, produces = "application/json")
	public List<Organization> getOrganizations (){
		return orgService.findAll();
	}
	
	@RequestMapping(value = "/insertOrganization", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String insertOrganization (@RequestBody OrganizationDTO org){
		return orgService.save(org);
	}
	
	@RequestMapping(value = "/updateOrganization", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String updateOrganization (@RequestBody OrganizationDTO org){
		return orgService.update(org);
	}
	
	@RequestMapping(value = "/deleteOrganization", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
	public String deleteOrganization (@RequestBody String id){
		return orgService.delete(id);
	}
	
	@RequestMapping(value = "/getMasters", method = RequestMethod.GET, produces = "application/json")
	public List<Organization> getMasters (){
		return orgService.findMaster();
	}
	
	@RequestMapping(value = "/getOrganizationByName", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public List<Organization> getOrganizationByName (@RequestBody String name){
		return orgService.findByName(name);
	}
	
	@RequestMapping(value = "/getOrganization", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public Organization getOrganization (@RequestBody String id){
		return orgService.findById(id);
	}
}
