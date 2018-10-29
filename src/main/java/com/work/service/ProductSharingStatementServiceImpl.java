package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.work.dao.ProductSharingStatementDAO;
import com.work.domain.ProductSharingStatement;
import com.work.domain.dto.ProductSharingStatementDTO;
import com.work.domain.dto.QuantityDTO;

@Service
public class ProductSharingStatementServiceImpl implements ProductSharingStatementService {

	@Autowired
	private ProductSharingStatementDAO pssDao;
	
	@Override
	public List<ProductSharingStatement> findAll() {
		return pssDao.findAll();
	}

	@Override
	public String save(ProductSharingStatementDTO pss) {
		Assert.notNull(pss, "Request contains no data");
		return pssDao.save(pss);
	}

	@Override
	public String update(ProductSharingStatementDTO pss) {
		Assert.notNull(pss, "Request contains no data");
		return pssDao.update(pss);
	}

	@Override
	public String delete(String id) {
		Assert.notNull(id, "No ID inside the request");
		return pssDao.delete(id);
	}

	@Override
	public ProductSharingStatement findById(String id) {
		Assert.notNull(id, "No ID inside the request");
		return pssDao.findById(id);
	}

	@Override
	public List<ProductSharingStatement> findBySharingOrganization(String sharingOrgId) {
		Assert.notNull(sharingOrgId, "No ID inside the request");
		return pssDao.findBySharingOrganization(sharingOrgId);
	}

	@Override
	public List<ProductSharingStatement> findByAccessingOrganization(String accessingOrgId) {
		Assert.notNull(accessingOrgId, "No ID inside the request");
		return pssDao.findByAccessingOrganization(accessingOrgId);
	}

	@Override
	public List<ProductSharingStatement> findByQuantity(QuantityDTO quant) {
		Assert.notNull(quant, "No quantity stated inside the request");
		return pssDao.findByQuantity(quant);
	}

	@Override
	public String approveStatement(String statId) {
		Assert.notNull(statId, "No ID inside the request");
		return pssDao.approveStatement(statId);
	}

	@Override
	public String disapproveStatement(String statId) {
		Assert.notNull(statId, "No ID inside the request");
		return pssDao.disapproveStatement(statId);
	}
	
}