package com.work.domain.dto;

public class AdminRoleDTO {

	private String id;
	private String adminId;
	private String roleId;
	
	public AdminRoleDTO(String id, String adminId, String roleId) {
		super();
		this.id = id;
		this.adminId = adminId;
		this.roleId = roleId;
	}
	
	public AdminRoleDTO() {
		super();
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
