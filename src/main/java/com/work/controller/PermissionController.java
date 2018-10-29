package com.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.domain.Permission;
import com.work.domain.dto.PermissionDTO;
import com.work.service.PermissionService;

@RequestMapping ("/permissions")
@RestController
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping (value = "/getPermissions", method = RequestMethod.GET, produces = "application/json")
	public List<Permission> getPermissions(){
		return permissionService.findAll();
	}
	@RequestMapping (value = "/insertPermission", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String insertPermission(@RequestBody PermissionDTO perm){
		return permissionService.save(perm);
	}
	@RequestMapping (value = "/updatePermission", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String updatePermission(@RequestBody PermissionDTO perm){
		return permissionService.update(perm);
	}
	@RequestMapping (value = "/deletePermissions", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
	public String deletePermissions(@RequestBody String id){
		return permissionService.delete(id);
	}
	@RequestMapping (value = "/getPermission", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public Permission getPermission(@RequestBody String id){
		return permissionService.findById(id);
	}
	@RequestMapping (value = "/getPermissionsByRole", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public List<Permission> getPermissionsByRole(@RequestBody String roleId){
		return permissionService.findByRoleId(roleId);
	}
	@RequestMapping (value = "/getPermissionsByEntity", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public List<Permission> getPermissionsByEntity(@RequestBody String entity){
		return permissionService.findByEntity(entity);
	}

}
