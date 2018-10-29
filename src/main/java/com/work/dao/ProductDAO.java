package com.work.dao;

import java.util.List;

import com.work.domain.Product;
import com.work.domain.dto.ProductDTO;
import com.work.domain.dto.ProductTransferObject;

public interface ProductDAO {
	List <Product> findAll();
	String save (ProductTransferObject pr);
	String update (ProductTransferObject pr);
	String delete(String id);
	Product findById(String id);
	List<Product> findByName(String name);
	List<Product> findByOrganization(String orgId);
	List<ProductDTO> getPermittedProducts(String orgId);

}
