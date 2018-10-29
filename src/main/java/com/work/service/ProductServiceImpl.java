package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.work.dao.ProductDAO;
import com.work.domain.Product;
import com.work.domain.dto.ProductDTO;
import com.work.domain.dto.ProductTransferObject;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO prodDao;
	
	
	@Override
	public List<Product> findAll() {
		return prodDao.findAll();
	}

	@Override
	public String save(ProductTransferObject pr) {
		Assert.notNull(pr, "Request contains no data");
		return prodDao.save(pr);
	}

	@Override
	public String update(ProductTransferObject pr) {
		Assert.notNull(pr, "Request contains no data");
		return prodDao.update(pr);
	}

	@Override
	public String delete(String id) {
		Assert.notNull(id, "No ID inside the request");
		return prodDao.delete(id);
	}

	@Override
	public Product findById(String id) {
		Assert.notNull(id, "No ID inside the request");
		return prodDao.findById(id);
	}

	@Override
	public List<Product> findByName(String name) {
		Assert.notNull(name, "No name inside the request");
		return prodDao.findByName(name);
	}

	@Override
	public List<Product> findByOrganization(String orgId) {
		Assert.notNull(orgId, "No ID inside the request");
		return prodDao.findByOrganization(orgId);
	}

	@Override
	public List<ProductDTO> getPermittedProducts(String orgId) {
		Assert.notNull(orgId, "No ID inside the request");
		return prodDao.getPermittedProducts(orgId);
	}
	
}