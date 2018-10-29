package com.work.domain;

import java.io.Serializable;

public class ProductSharingStatement implements Serializable{

	private String id;
	private String sharingOrgId;
	private String accessingOrgId;
	private Long quantity;
	private Relation relation;
	private Boolean approved;
	private Long price;
	private String roleId;
	
	public ProductSharingStatement(String id, String sharingOrgId, String accessingOrgId, Long quantity,
			Relation relation, Boolean approved, Long price, String roleId) {
		super();
		this.id = id;
		this.sharingOrgId = sharingOrgId;
		this.accessingOrgId = accessingOrgId;
		this.quantity = quantity;
		this.relation = relation;
		this.approved = approved;
		this.price = price;
		this.roleId = roleId;
	}

	public ProductSharingStatement() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSharingOrgId() {
		return sharingOrgId;
	}

	public void setSharingOrgId(String sharingOrgId) {
		this.sharingOrgId = sharingOrgId;
	}

	public String getAccessingOrgId() {
		return accessingOrgId;
	}

	public void setAccessingOrgId(String accessingOrgId) {
		this.accessingOrgId = accessingOrgId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
	
}
