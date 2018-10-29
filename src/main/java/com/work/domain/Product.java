package com.work.domain;

import java.io.Serializable;

public class Product implements Serializable{

	private String id;
	private String name;
	private String description;
	private Double price;
	private String organizationId;
	
	public Product(String id, String name, String description, Double price, String organizationId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.organizationId = organizationId;
	}

	public Product() {
		super();
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
	
}
