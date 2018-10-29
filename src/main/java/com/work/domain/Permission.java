package com.work.domain;

import java.io.Serializable;

public class Permission implements Serializable{

	private String id;
	private Entity entity;
	private Operation operation;
	private String roleId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Permission() {
		super();
	}

	public Permission(String id, Entity entity, Operation operation, String roleId) {
		super();
		this.id = id;
		this.entity = entity;
		this.operation = operation;
		this.roleId = roleId;
	}
	
	
}
