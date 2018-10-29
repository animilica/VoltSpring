package com.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.domain.Role;
import com.work.domain.dto.RoleDTO;
import com.work.service.RoleService;

@RequestMapping("/roles")
@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping (value = "/getRoles", method = RequestMethod.GET, produces = "application/json")
	public List <Role> getRoles(){
		return roleService.findAll();
	}
	
	@RequestMapping(value = "/insertRole", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String insertRole (@RequestBody RoleDTO r){
		return roleService.save(r);
	}
	
	@RequestMapping(value = "/updateRole", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String updateRole (@RequestBody RoleDTO r){
		return roleService.update(r);
	}
	
	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
	public String deleteRole (@RequestBody String id){
		return roleService.delete(id);
	}
	
	
	@RequestMapping(value = "/getRoleByName", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public List<Role> getRoleByName (@RequestBody String name){
		return roleService.findByName(name);
	}
	
	@RequestMapping(value = "/getRole", method = RequestMethod.POST, consumes = "text/plain", produces = "application/plain")
	public Role getRole (@RequestBody String id){
		return roleService.findById(id);
	}
	
	@RequestMapping(value = "/getRoleByOrganization", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public List<Role> getRoleByOrganization (@RequestBody String orgId){
		return roleService.findByOrganization(orgId);
	}
}
