package com.work.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.work.dao.ProductSharingStatementDAO;
import com.work.dao.RoleDAO;
import com.work.domain.ProductSharingStatement;
import com.work.domain.Relation;
import com.work.domain.Role;
import com.work.domain.dto.ProductSharingStatementDTO;
import com.work.domain.dto.QuantityDTO;
import com.work.mapping.ProductSharingStatementRowMapper;

@Repository
public class ProductSharingStatementDAOImpl implements ProductSharingStatementDAO{
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	private static final String selectAllSQL = "SELECT * FROM PRODUCTSHARINGSTATEMENT";
	private static final String insertSQL = "INSERT INTO PRODUCTSHARINGSTATEMENT (ID, SHARINGORGID, ACCESSINGORGID, QUANTITY, RELATION, APPROVED, PRICE, ROLE_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String updateSQL = "UPDATE PRODUCTSHARINGSTATEMENT SET SHARINGORGID = ?, ACCESSINGORGID = ?, QUANTITY = ?, RELATION = ?, APPROVED = ?, PRICE = ?, ROLE_ID = ? WHERE ID = ?";
	private static final String deleteSQL = "DELETE FROM PRODUCTSHARINGSTATEMENT WHERE ID = ?";
	private static final String findByIDSQL = "SELECT * FROM PRODUCTSHARINGSTATEMENT WHERE ID = ?";
	private static final String findBySharingIDSQL = "SELECT * FROM PRODUCTSHARINGSTATEMENT WHERE SHARINGORGID = ?";
	private static final String findByAccessingIDSQL = "SELECT * FROM PRODUCTSHARINGSTATEMENT WHERE ACCESSINGORGID = ?";
	private static final String quantityBetweenSQL = "SELECT * FROM PRODUCTSHARINGSTATEMENT WHERE QUANTITY > ? AND QUANTITY < ?";
	private static final String quantityAboveSQL = "SELECT * FROM PRODUCTSHARINGSTATEMENT WHERE QUANTITY > ?";
	private static final String quantityBelowSQL = "SELECT * FROM PRODUCTSHARINGSTATEMENT WHERE QUANTITY < ?";
	private static final String approveSQL = "UPDATE PRODUCTSHARINGSTATEMENT SET APPROVED = 1 WHERE ID = ?";
	private static final String disapproveSQL = "UPDATE PRODUCTSHARINGSTATEMENT SET APPROVED = 0 WHERE ID = ?";
	
	@Value("${spring.datasource.url}")
	private String connectionName;
	
	@Autowired
	private RoleDAO roleDao;
	
	@Override
	public List <ProductSharingStatement> findAll(){

		List <ProductSharingStatement> prms = new ArrayList <ProductSharingStatement>();
		RowMapper<ProductSharingStatement> rowMapper = new ProductSharingStatementRowMapper();
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			Statement query = conn.createStatement();
			ResultSet rs = query.executeQuery(selectAllSQL);
			while(rs.next()) {
				ProductSharingStatement pss = rowMapper.mapRow(rs, rs.getRow());
				prms.add(pss);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return prms;
	}
	
	@Override
	public String save(ProductSharingStatementDTO p){
		
		Role role = roleDao.findById(p.getRoleId());
		
		if (role == null) {
			return "No such Role";
		}

		try {
			boolean contains = false;
			for (Relation rel : Relation.values()) {
				if ( rel.name().equals(p.getRelation())) {
					contains = true;
				}
			}
			if (!contains) {
				return "Improper value for Entity";
			}
			
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(insertSQL);
			prpStmt.setString(2, p.getSharingOrgId());
			prpStmt.setString(3, p.getAccessingOrgId());
			prpStmt.setString(4, p.getQuantity());
			prpStmt.setString(5, p.getRelation());
			prpStmt.setInt(6, p.getApproved());
			prpStmt.setString(7,  p.getPrice());
			prpStmt.setString(8, p.getRoleId());
			UUID uuid = UUID.randomUUID();
			prpStmt.setString(1, uuid.toString());
			prpStmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}
		
		return "Success";
	}

	@Override
	public String update(ProductSharingStatementDTO pss) {
		
		Role role = roleDao.findById(pss.getRoleId());
		
		if (role == null) {
			return "No such Role";
		}
	
		try {
			boolean contains = false;
			for (Relation rel : Relation.values()) {
				if ( rel.name().equals(pss.getRelation())) {
					contains = true;
				}
			}
			if (!contains) {
				return "Improper value for Entity";
			}
			
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(updateSQL);
			prpStmt.setString(1, pss.getSharingOrgId());
			prpStmt.setString(2, pss.getAccessingOrgId());
			prpStmt.setString(3, pss.getQuantity());
			prpStmt.setString(4, pss.getRelation());
			prpStmt.setInt(5, pss.getApproved());
			prpStmt.setString(6, pss.getPrice());
			prpStmt.setString(7, pss.getRoleId());
			prpStmt.setString(7, pss.getId());
			prpStmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}
		
		return "Success";
	}

	@Override
	public String delete(String id) {
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(deleteSQL);
			prpStmt.setString(1, id);
			prpStmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}
		
		return "Success";
	}

	@Override
	public ProductSharingStatement findById(String id) {
		 
		ProductSharingStatement pss = null;
		RowMapper<ProductSharingStatement> rowMapper = new ProductSharingStatementRowMapper();

	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByIDSQL);
			prpStmt.setString(1, id);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				pss = rowMapper.mapRow(rs, rs.getRow());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return pss;
	}

	@Override
	public List<ProductSharingStatement> findBySharingOrganization(String sharingOrgId) {
		List <ProductSharingStatement> prms = new ArrayList <ProductSharingStatement>();
		RowMapper<ProductSharingStatement> rowMapper = new ProductSharingStatementRowMapper();
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findBySharingIDSQL);
			prpStmt.setString(1, sharingOrgId);
			ResultSet rs = prpStmt.executeQuery();
			while(rs.next()) {
				ProductSharingStatement pss = rowMapper.mapRow(rs, rs.getRow());
				prms.add(pss);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return prms;
	}

	@Override
	public List<ProductSharingStatement> findByAccessingOrganization(String accessingOrgId) {
		List <ProductSharingStatement> prms = new ArrayList <ProductSharingStatement>();
		RowMapper<ProductSharingStatement> rowMapper = new ProductSharingStatementRowMapper();
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByAccessingIDSQL);
			prpStmt.setString(1, accessingOrgId);
			ResultSet rs = prpStmt.executeQuery();
			while(rs.next()) {
				ProductSharingStatement pss = rowMapper.mapRow(rs, rs.getRow());
				prms.add(pss);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return prms;
	}

	@Override
	public List<ProductSharingStatement> findByQuantity(QuantityDTO quant) {

		List <ProductSharingStatement> prms = new ArrayList <ProductSharingStatement>();
		RowMapper<ProductSharingStatement> rowMapper = new ProductSharingStatementRowMapper();
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			if (quant.getLower().equals("")) {
				prpStmt = conn.prepareStatement(quantityBelowSQL);
				prpStmt.setString(1, quant.getUpper());
			} else if (quant.getUpper().equals("")) {
				prpStmt = conn.prepareStatement(quantityAboveSQL);
				prpStmt.setString(1, quant.getLower());
			} else {
				prpStmt = conn.prepareStatement(quantityBetweenSQL);
				prpStmt.setString(1, quant.getLower());
				prpStmt.setString(2, quant.getUpper());
			}
			ResultSet rs = prpStmt.executeQuery();
			while(rs.next()) {
				ProductSharingStatement pss = rowMapper.mapRow(rs, rs.getRow());
				prms.add(pss);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return prms;
	}
	
	
	@Override
	public String approveStatement(String statId) {
		
		try {
			
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(approveSQL);
			prpStmt.setString(1, statId);
			prpStmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}
		
		return "Success";
	}
	
	
	@Override
	public String disapproveStatement(String statId) {
		
		try {
			
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(disapproveSQL);
			prpStmt.setString(1, statId);
			prpStmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}
		
		return "Success";
	}

	
}
