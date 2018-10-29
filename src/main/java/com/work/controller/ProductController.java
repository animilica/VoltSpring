package com.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.domain.Product;
import com.work.domain.dto.ProductDTO;
import com.work.domain.dto.ProductTransferObject;
import com.work.service.ProductService;

@RequestMapping("/products")
@RestController
public class ProductController {
	
	@Autowired 
	private ProductService productService;
	
	@RequestMapping(value="/getProducts", method =  RequestMethod.GET, produces = "application/json")
	public List<Product> getProducts(){
		return productService.findAll();
	}
	@RequestMapping (value = "/insertProduct", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String insertProduct(@RequestBody ProductTransferObject prod){
		return productService.save(prod);
	}
	@RequestMapping (value = "/updateProduct", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String updateProduct(@RequestBody ProductTransferObject prod){
		return productService.update(prod);
	}
	@RequestMapping (value = "/deleteProduct", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
	public String deleteProduct(@RequestBody String id){
		return productService.delete(id);
	}
	@RequestMapping (value = "/getProduct", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public Product getProduct(@RequestBody String id){
		return productService.findById(id);
	}
	@RequestMapping (value = "/getProductsByOrganization", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public List<Product> getProductsByOrganization(@RequestBody String orgId){
		return productService.findByOrganization(orgId);
	}
	@RequestMapping (value = "/getPermittedProducts", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public List<ProductDTO> getPermittedProducts(@RequestBody String orgId){
		return productService.getPermittedProducts(orgId);
	}

}
