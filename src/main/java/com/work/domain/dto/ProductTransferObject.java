package com.work.domain.dto;

public class ProductTransferObject {

	private String id;
	private String name;
	private String description;
	private String price;
	private String organizationId;
	private String adminId;
	
	public ProductTransferObject(String id, String name,
			String description, String price, String organizationId, String adminId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.organizationId = organizationId;
		this.adminId = adminId;
	}

	public ProductTransferObject() {
		super();
	}
	

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
	
}
