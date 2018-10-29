package com.work.domain.dto;

public class QuantityDTO {
	
	String lower;
	String upper;
	public String getLower() {
		return lower;
	}
	public void setLower(String lower) {
		this.lower = lower;
	}
	public String getUpper() {
		return upper;
	}
	public void setUpper(String upper) {
		this.upper = upper;
	}
	public QuantityDTO(String lower, String upper) {
		super();
		this.lower = lower;
		this.upper = upper;
	}
	public QuantityDTO() {
		super();
	}
	

}
