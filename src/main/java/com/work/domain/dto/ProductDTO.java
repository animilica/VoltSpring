package com.work.domain.dto;

import java.util.ArrayList;

import com.work.domain.Operation;
import com.work.domain.Product;

public class ProductDTO {
	private Product product;
	private ArrayList<Operation> allowedOps;
	
	public ProductDTO(Product product, ArrayList<Operation> allowedOps) {
		super();
		this.product = product;
		this.allowedOps = allowedOps;
	}

	public ProductDTO() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ArrayList<Operation> getAllowedOps() {
		return allowedOps;
	}

	public void setAllowedOps(ArrayList<Operation> allowedOps) {
		this.allowedOps = allowedOps;
	}
	
	

}
