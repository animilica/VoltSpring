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

import com.work.dao.AdminDAO;
import com.work.dao.AdminRoleDAO;
import com.work.dao.OrganizationDAO;
import com.work.dao.PermissionDAO;
import com.work.dao.ProductDAO;
import com.work.dao.ProductSharingStatementDAO;
import com.work.dao.RoleDAO;
import com.work.domain.Admin;
import com.work.domain.AdminRole;
import com.work.domain.Operation;
import com.work.domain.Organization;
import com.work.domain.Permission;
import com.work.domain.Product;
import com.work.domain.ProductSharingStatement;
import com.work.domain.Role;
import com.work.domain.dto.ProductDTO;
import com.work.domain.dto.ProductTransferObject;
import com.work.mapping.ProductRowMapper;

@Repository
public class ProductDAOImpl implements ProductDAO{

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	@Value("${spring.datasource.url}")
	private String connectionName;
	
	@Autowired
	private ProductSharingStatementDAO pssDAO;
	
	@Autowired
	private AdminDAO adDAO;
	
	@Autowired
	private AdminRoleDAO arDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private PermissionDAO prmDAO;
	
	@Autowired
	private OrganizationDAO orgDAO;
	
	private static final String selectAllSQL = "SELECT * FROM PRODUCT";
	private static final String insertSQL = "INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE, ORGANIZATION_ID) VALUES (?, ?, ?, ?, ?)";
	private static final String updateSQL = "UPDATE PRODUCT SET NAME = ?, PRICE = ?, DESCRIPTION = ?, ORGANIZATION_ID = ? WHERE ID = ?";
	private static final String deleteSQL = "DELETE FROM PRODUCT WHERE ID = ?";
	private static final String selectByOrganizationIDSQL = "SELECT * FROM PRODUCT WHERE ORGANIZATION_ID = ?";
	private static final String selectPermittedProductsSQL = "SELECT * FROM PRODUCT p, PRODUCTSHARINGSTATEMENT ps WHERE ps.sharingorgid = ? AND p.organization_id = ps.sharingorgid AND ps.approved = 1 ";
	private static final String findByIDSQL = "SELECT * FROM PRODUCT WHERE ID = ?";
	private static final String findByNameSQL = "SELECT * FROM PRODUCT WHERE NAME = ?";
	
	@Override
	public List <Product> findAll(){

	 
		List<Product> prods = new ArrayList<Product>();
		RowMapper<Product> rowMapper = new ProductRowMapper();

	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			Statement query = conn.createStatement();
			ResultSet rs = query.executeQuery(selectAllSQL);
			while (rs.next()) {
				Product prod = rowMapper.mapRow(rs, rs.getRow());
				prods.add(prod);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return prods;
	}
	
	@Override
	public List <ProductDTO> getPermittedProducts(String admId){
		
		Admin adm = adDAO.findById(admId);
		String orgId = adm.getOrganizationId();
		Role rola = null;
		List<AdminRole> ars = arDAO.findByAdminId(admId);
		for (AdminRole ar : ars) {
			Role aRola = roleDAO.findById(ar.getRoleID());
			if (aRola.getOrganizationId().equals(orgId)) {
				rola = aRola;
			}
		}
		
		List<ProductDTO> prods = new ArrayList<ProductDTO>();
		RowMapper<Product> rowMapper = new ProductRowMapper();
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(selectByOrganizationIDSQL);
			prpStmt.setString(1, orgId);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				Product prod = rowMapper.mapRow(rs, rs.getRow());
				ProductDTO pDTO = new ProductDTO();
				pDTO.setAllowedOps(new ArrayList<Operation>());
				pDTO.setProduct(prod);
				List<Permission> perms = prmDAO.findByRoleId(rola.getId());
				for (Permission prm : perms) {
					pDTO.getAllowedOps().add(prm.getOperation());
				}
				prods.add(pDTO);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		List<ProductSharingStatement> psList = pssDAO.findByAccessingOrganization(orgId);
		List<String> sharingOrg = new ArrayList<String>();
		for (ProductSharingStatement pss : psList) {
			if (!sharingOrg.contains(pss.getSharingOrgId())) {
				sharingOrg.add(pss.getSharingOrgId());
			}
		}
		for (String orgShar : sharingOrg) {
			String sqlAppend = "";
			List<String> statements = new ArrayList<String>();
			List<String> roles = new ArrayList<String>();
			
			for (ProductSharingStatement pss : psList) {
				
				if(pss.getPrice()!=null && pss.getPrice()!=0) {
					sqlAppend = sqlAppend.concat("AND ps.RELATION = '");
					sqlAppend = sqlAppend.concat(pss.getRelation().toString());
					sqlAppend = sqlAppend.concat("'");
					sqlAppend = sqlAppend.concat(" AND (ps.PRICE ");
					String relation = pss.getRelation().toString();
					switch(relation) {
					case "EQ" : sqlAppend = sqlAppend.concat("= "); break;
					case "GT" : sqlAppend = sqlAppend.concat("> "); break;
					case "GTE" : sqlAppend = sqlAppend.concat(">= "); break;
					case "LT" : sqlAppend = sqlAppend.concat("< "); break;
					case "LTE" : sqlAppend = sqlAppend.concat("=< "); break;
					}
					
					sqlAppend = sqlAppend.concat(pss.getPrice().toString());
					sqlAppend = sqlAppend.concat(") ");
					
				}
				
				if(pss.getQuantity()!=null) {
					sqlAppend = sqlAppend.concat("AND ps.RELATION = '");
					sqlAppend = sqlAppend.concat(pss.getRelation().toString());
					sqlAppend = sqlAppend.concat("'");
					sqlAppend = sqlAppend.concat(" AND (CAST(p.DESCRIPTION AS BIGINT) ");
					String relation = pss.getRelation().toString();
					switch(relation) {
					case "EQ" : sqlAppend = sqlAppend.concat("= "); break;
					case "GT" : sqlAppend = sqlAppend.concat("> "); break;
					case "GTE" : sqlAppend = sqlAppend.concat(">= "); break;
					case "LT" : sqlAppend = sqlAppend.concat("< "); break;
					case "LTE" : sqlAppend = sqlAppend.concat("=< "); break;
					}
					sqlAppend = sqlAppend.concat(pss.getQuantity().toString());
					sqlAppend = sqlAppend.concat(")");
				}
				
				String newSql = selectPermittedProductsSQL.concat(sqlAppend);
				statements.add(newSql);
				roles.add(pss.getRoleId());
				sqlAppend = "";
			}
			
			try {
				Connection conn = DriverManager.getConnection(connectionName);
				int i =0;
				for (String sqlString : statements) {
					PreparedStatement prpStmt = null;
					List<Permission> perms = prmDAO.findByRoleId(roles.get(i));
					i = i+1;
					prpStmt = conn.prepareStatement(sqlString);
					prpStmt.setString(1, orgShar);
					ResultSet rs = prpStmt.executeQuery();
					while (rs.next()) {
						Product prod = rowMapper.mapRow(rs, rs.getRow());
						ProductDTO pDTO = new ProductDTO();
						pDTO.setProduct(prod);
						pDTO.setAllowedOps(new ArrayList<Operation>());
						
						for (Permission prm : perms) {
							pDTO.getAllowedOps().add(prm.getOperation());
						}
						prods.add(pDTO);
					}
					
				}
				
				
				
			
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return prods;
	}
	
	
	@Override
	public String save(ProductTransferObject p){
		
		Organization org = orgDAO.findById(p.getOrganizationId());
		if (org == null) {
			return "No such organization";
		}

		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(insertSQL);
			prpStmt.setString(2, p.getName());
			prpStmt.setString(3, p.getDescription());
			prpStmt.setDouble(4, Double.parseDouble(p.getPrice()));
			prpStmt.setString(5, p.getOrganizationId());
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
	public String update(ProductTransferObject pr) {
		
		Organization org = orgDAO.findById(pr.getOrganizationId());
		if (org == null) {
			return "No such organization";
		}
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(updateSQL);
			prpStmt.setString(1, pr.getName());
			prpStmt.setString(3, pr.getDescription());
			prpStmt.setDouble(2, Double.parseDouble(pr.getPrice()));
			prpStmt.setString(4, pr.getOrganizationId());
			prpStmt.setString(5, pr.getId());
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
	public Product findById(String id) {
		 
		Product prod = null;
		RowMapper<Product> rowMapper = new ProductRowMapper();

	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByIDSQL);
			prpStmt.setString(1, id);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				prod = rowMapper.mapRow(rs, rs.getRow());
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return prod;
	}

	@Override
	public List<Product> findByName(String name) {
	 
		List<Product> prods = new ArrayList<Product>();
		RowMapper<Product> rowMapper = new ProductRowMapper();

	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByNameSQL);
			prpStmt.setString(1, name);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				Product prod = rowMapper.mapRow(rs, rs.getRow());
				prods.add(prod);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return prods;
	}

	

	@Override
	public List<Product> findByOrganization(String orgId) {

	 
		List<Product> prods = new ArrayList<Product>();
		RowMapper<Product> rowMapper = new ProductRowMapper();

	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(selectByOrganizationIDSQL);
			prpStmt.setString(1, orgId);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				Product prod = rowMapper.mapRow(rs, rs.getRow());
				prods.add(prod);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return prods;
	}
	
}
