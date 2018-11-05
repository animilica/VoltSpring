package com.work.dao.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.work.dao.OrganizationDAO;
import com.work.domain.Organization;
import com.work.domain.dto.OrganizationDTO;
import com.work.mapping.OrganizationRowMapper;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO{

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	private static final String selectAllSQL = "SELECT * FROM ORGANIZATION";
	private static final String insertSQL = "INSERT INTO ORGANIZATION (ID, NAME, MASTER) VALUES (?, ?, ?)";
	private static final String updateSQL = "UPDATE ORGANIZATION SET NAME = ?, MASTER = ? WHERE ID = ?";
	private static final String deleteSQL = "DELETE FROM ORGANIZATION WHERE ID = ?";
	private static final String findMasterSQL = "SELECT * FROM ORGANIZATION WHERE MASTER = 1";
	private static final String findByNameSQL = "SELECT * FROM ORGANIZATION WHERE NAME LIKE ?";
	private static final String findByIDSQL = "SELECT * FROM ORGANIZATION WHERE ID = ?";
	
	@Value("${spring.datasource.url}")
	private String connectionName;
	
	@Override
	public List <Organization> findAll() {

	 
		List<Organization> orgs = new ArrayList<Organization>();
		RowMapper<Organization> rowMapper = new OrganizationRowMapper();

	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			Statement query = conn.createStatement();
			ResultSet rs = query.executeQuery(selectAllSQL);
			while (rs.next()) {
				Organization org = rowMapper.mapRow(rs, rs.getRow());
				orgs.add(org);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return orgs;
	}
	
	@Override
	public String save(OrganizationDTO o) {
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(insertSQL);
			prpStmt.setString(2, o.getName());
			prpStmt.setInt(3, o.getMaster());
			UUID uuid = UUID.randomUUID();
			prpStmt.setString(1, uuid.toString());
			prpStmt.execute();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure!";
		}
		
		return "Success!";
		
	}

	@Override
	public String update(OrganizationDTO o) {
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(updateSQL);
			prpStmt.setInt(2, o.getMaster());
			prpStmt.setString(3, o.getId());
			prpStmt.setString(1, o.getName());
			prpStmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure!";
		}
		
		return "Success!";
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
			return "Failure!";
		}
		
		return "Success!";
	}

	@Override
	public List<Organization> findMaster() {
		 
		List<Organization> orgs = new ArrayList<Organization>();
		RowMapper<Organization> rowMapper = new OrganizationRowMapper();

	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			Statement query = conn.createStatement();
			ResultSet rs = query.executeQuery(findMasterSQL);
			while (rs.next()) {
				Organization org = rowMapper.mapRow(rs, rs.getRow());
				orgs.add(org);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return orgs;
	}

	@Override
	public List<Organization> findByName(String name) {
		 
		List<Organization> orgs = new ArrayList<Organization>();
		RowMapper<Organization> rowMapper = new OrganizationRowMapper();
	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByNameSQL);
			prpStmt.setString(1, '%' + name +'%');
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				Organization org = rowMapper.mapRow(rs, rs.getRow());
				orgs.add(org);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return orgs;
	}

	@Override
	public Organization findById(String id) {
		 
		Organization org = null;
		RowMapper<Organization> rowMapper = new OrganizationRowMapper();

	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByIDSQL);
			prpStmt.setString(1, id);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				org = rowMapper.mapRow(rs, rs.getRow());
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return org;
	}

}
