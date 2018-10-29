package com.work.domain.dto;

public class PermissionDTO {

	private String entity;
	private String operation;
	private String roleId;
	private String id;

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
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

	public PermissionDTO() {
		super();
	}

	public PermissionDTO(String id, String entity, String operation, String roleId) {
		super();
		this.id = id;
		this.entity = entity;
		this.operation = operation;
		this.roleId = roleId;
	}
	
	
}
