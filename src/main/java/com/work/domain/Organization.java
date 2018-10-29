package com.work.domain;

import java.io.Serializable;

public class Organization implements Serializable{
	private String id;
	private String name;
	private Boolean master;
	public Organization(String id, String name, Boolean master) {
		super();
		this.id = id;
		this.name = name;
		this.master = master;
	}
	public Organization() {
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
	public Boolean getMaster() {
		return master;
	}
	public void setMaster(Boolean master) {
		this.master = master;
	}
	
	
}
