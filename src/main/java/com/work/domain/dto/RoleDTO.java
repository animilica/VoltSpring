package com.work.domain.dto;

public class RoleDTO {

	private String name;
	private String organizationId;
	private String id;
	
	
	public RoleDTO() {
		super();
	}

	public RoleDTO(String id, String name, String organizationId) {
		super();
		this.id =id;
		this.name = name;
		this.organizationId = organizationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
