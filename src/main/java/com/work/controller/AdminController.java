package com.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.domain.Admin;
import com.work.domain.dto.AdminDTO;
import com.work.service.AdminService;

@RequestMapping("/admins")
@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/getAdmins", method = RequestMethod.GET, produces = "application/json")
	public List<Admin> getAdmins (){
		return adminService.findAll();
	}
	
	@RequestMapping(value = "/insertAdmin", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String insertAdmin (@RequestBody AdminDTO admin){
		return adminService.save(admin);
	}
	
	@RequestMapping(value = "/updateAdmin", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String updateAdmin (@RequestBody AdminDTO a){
		return adminService.update(a);
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String changePassword (@RequestBody AdminDTO a){
		return adminService.changePassword(a);
	}
	
	@RequestMapping(value = "/deleteAdmin", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
	public String deleteAdmin (@RequestBody String id){
		return adminService.delete(id);
	}
	
	@RequestMapping(value = "/getAdminByFirstName", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public List<Admin> getAdminByFirstName (@RequestBody String first_name){
		return adminService.findByFirstName(first_name);
	}
	
	@RequestMapping(value = "/getAdminByLastName", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public List<Admin> getAdminByLastName (@RequestBody String last_name){
		return adminService.findByLastName(last_name);
	}
	
	@RequestMapping(value = "/getAdminByEmail", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public Admin getAdminByEmail (@RequestBody String email){
		return adminService.findByEmail(email);
	}
	
	@RequestMapping(value = "/getAdminById", method = RequestMethod.POST, consumes = "text/plain", produces = "application/plain")
	public Admin getAdminById (@RequestBody String id){
		return adminService.findById(id);
	}
	
	@RequestMapping(value = "/getAdminByOrgId", method = RequestMethod.POST, consumes = "text/plain", produces = "application/plain")
	public Admin getAdminByOrgId (@RequestBody String orgId){
		return adminService.findByOrganization(orgId);
	}
	
}
