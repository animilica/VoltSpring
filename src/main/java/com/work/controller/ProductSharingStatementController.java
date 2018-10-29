package com.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.domain.ProductSharingStatement;
import com.work.domain.dto.ProductSharingStatementDTO;
import com.work.domain.dto.QuantityDTO;
import com.work.service.ProductSharingStatementService;

@RequestMapping("/productSharingStatements")
@RestController
public class ProductSharingStatementController {

	@Autowired
	private ProductSharingStatementService productSSService;
	
	@RequestMapping(value = "/getProductSharingStatements", method = RequestMethod.GET, produces = "application/json")
	public List <ProductSharingStatement> getProductSharingStatements (){
		return productSSService.findAll();
	}
	
	@RequestMapping (value = "/insertProductSharingStatement", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String insertProduct(@RequestBody ProductSharingStatementDTO prod){
		return productSSService.save(prod);
	}
	@RequestMapping (value = "/updateProductSharingStatements", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String updateProduct(@RequestBody ProductSharingStatementDTO prod){
		return productSSService.update(prod);
	}
	@RequestMapping (value = "/deleteProductSharingStatements", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
	public String deleteProduct(@RequestBody String id){
		return productSSService.delete(id);
	}
	@RequestMapping (value = "/getProductSharingStatement", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public ProductSharingStatement getProduct(@RequestBody String id){
		return productSSService.findById(id);
	}
	@RequestMapping(value = "/getProductSharingStatementsBySharingOrg", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public List <ProductSharingStatement> getProductSharingStatementsBySharingOrg (@RequestBody String id){
		return productSSService.findBySharingOrganization(id);
	}
	@RequestMapping(value = "/getProductSharingStatementsByAccessOrg", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public List <ProductSharingStatement> getProductSharingStatementsByAccessOrg (@RequestBody String id){
		return productSSService.findByAccessingOrganization(id);
	}
	@RequestMapping(value = "/getProductSharingStatementsByQuantity", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public List <ProductSharingStatement> getProductSharingStatementsByQuantity (@RequestBody QuantityDTO quant){
		return productSSService.findByQuantity(quant);
	}
	
	@RequestMapping (value = "/approveStatement", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
	public String approveStatement(@RequestBody String statId){
		return productSSService.approveStatement(statId);
	}
	@RequestMapping (value = "/disapproveStatement", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
	public String disapproveStatement(@RequestBody String statId){
		return productSSService.disapproveStatement(statId);
	}
}
