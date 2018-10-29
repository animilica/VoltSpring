package com.work.service;

import java.util.List;

import com.work.domain.ProductSharingStatement;
import com.work.domain.dto.ProductSharingStatementDTO;
import com.work.domain.dto.QuantityDTO;

public interface ProductSharingStatementService {

	List <ProductSharingStatement> findAll();
	String save (ProductSharingStatementDTO pss);
	String update(ProductSharingStatementDTO pss);
	String delete(String id);
	ProductSharingStatement findById(String id);
	List<ProductSharingStatement> findBySharingOrganization(String sharingOrgId);
	List<ProductSharingStatement> findByAccessingOrganization(String accessingOrgId);
	List<ProductSharingStatement> findByQuantity(QuantityDTO quant);
	String approveStatement(String statId);
	String disapproveStatement(String statId);
}