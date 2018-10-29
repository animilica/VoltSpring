package com.work.domain.dto;

public class ProductSharingStatementDTO {

	private String id;
	private String sharingOrgId;
	private String accessingOrgId;
	private String quantity;
	private String relation;
	private Integer approved;
	private String price;
	private String roleId;
	

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
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public Integer getApproved() {
		return approved;
	}
	public void setApproved(Integer approved) {
		this.approved = approved;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public ProductSharingStatementDTO(String id, String sharingOrgId, String accessingOrgId, String quantity, String relation,
			Integer approved, String price, String roleId) {
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
	
	public ProductSharingStatementDTO() {
		super();
	}

	
	
	
}
