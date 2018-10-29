package com.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.domain.AdminRole;
import com.work.domain.dto.AdminRoleDTO;
import com.work.service.AdminRoleService;

@RequestMapping ("/adminRoles")
@RestController
public class AdminRoleController {
	
	@Autowired
	private AdminRoleService adminRoleService;
	
	@RequestMapping (value = "/getAdminRoles", method = RequestMethod.GET, produces = "application/json")
	public List<AdminRole> getAdminRoles(){
		return adminRoleService.findAll();
	}
	
	@RequestMapping(value = "/insertAdminRole", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String insertAdminRole (@RequestBody AdminRoleDTO ar){
		return adminRoleService.save(ar);
	}
	
	@RequestMapping(value = "/updateAdminRole", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String updateAdminRole (@RequestBody AdminRoleDTO ar){
		return adminRoleService.update(ar);
	}
	
	@RequestMapping(value = "/deleteAdminRole", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
	public String deleteAdminRole (@RequestBody String id){
		return adminRoleService.delete(id);
	}

	@RequestMapping(value = "/getAdminRoleById", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public AdminRole getAdminRoleById (@RequestBody String id){
		return adminRoleService.findById(id);
	}
	@RequestMapping(value = "/getAdminRoleByAdminId", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public List<AdminRole> getAdminRoleByAdminId (@RequestBody String adminId){
		return adminRoleService.findByAdminId(adminId);
	}
	@RequestMapping(value = "/getAdminRoleByRoleId", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public AdminRole getAdminRoleByRoleId (@RequestBody String roleId){
		return adminRoleService.findByRoleId(roleId);
	}
}
