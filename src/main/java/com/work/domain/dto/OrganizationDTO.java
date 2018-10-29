package com.work.domain.dto;

public class OrganizationDTO {
	
	private String id;
	private String name;
	private Integer master;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMaster() {
		return master;
	}
	public void setMaster(Integer master) {
		this.master = master;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public OrganizationDTO(String id, String name, Integer master) {
		super();
		this.id = id;
		this.name = name;
		this.master = master;
	}
	public OrganizationDTO() {
		super();
	}
	
	

}
