package com.work.domain;

import java.io.Serializable;


public class AdminRole implements Serializable {
	
	private String id;
	
	
	private String adminId;
	private String roleID;
	
	public AdminRole(String id, String adminId, String roleID) {
		super();
		this.id = id;
		this.adminId = adminId;
		this.roleID = roleID;
	}
	
	public AdminRole() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	
}
