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

import com.work.dao.OrganizationDAO;
import com.work.dao.RoleDAO;
import com.work.domain.Organization;
import com.work.domain.Role;
import com.work.domain.dto.RoleDTO;
import com.work.mapping.RoleRowMapper;


@Repository
public class RoleDAOImpl implements RoleDAO{

	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	private static final String selectAllSQL = "SELECT * FROM ROLE";
	private static final String insertSQL = "INSERT INTO ROLE (ID, NAME, ORGANIZATION_ID) VALUES (?, ?, ?)";
	private static final String updateSQL = "UPDATE ROLE SET NAME = ?, ORGANIZATION_ID = ? WHERE ID = ?";
	private static final String deleteSQL = "DELETE FROM ROLE WHERE ID = ?";
	private static final String findByIDSQL = "SELECT * FROM ROLE WHERE ID = ?";
	private static final String findByNameSQL = "SELECT * FROM ROLE WHERE NAME = ?";
	private static final String findByOrganizationIDSQL = "SELECT * FROM ROLE WHERE ORGANIZATION_ID = ?";
	
	@Value("${spring.datasource.url}")
	private String connectionName;
	
	@Autowired
	private OrganizationDAO orgDAO;
	
	@Override
	public List <Role> findAll(){

	 
		List<Role> roles = new ArrayList<Role>();
		RowMapper<Role> rowMapper = new RoleRowMapper();

		try {
			Connection conn = DriverManager.getConnection(connectionName);
			Statement query = conn.createStatement();
			ResultSet rs = query.executeQuery(selectAllSQL);
			while (rs.next()) {
				Role role = rowMapper.mapRow(rs, rs.getRow());
				roles.add(role);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	 
		return roles;
	}
	
	@Override
	public String save(RoleDTO r){
		
		Organization org = orgDAO.findById(r.getOrganizationId());
		if (org == null) {
			return "No such organization";
		}
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(insertSQL);
			prpStmt.setString(2, r.getName());
			prpStmt.setString(3, r.getOrganizationId());
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
	public String update(RoleDTO r) {
		
		Organization org = orgDAO.findById(r.getOrganizationId());
		if (org == null) {
			return "No such organization";
		}
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(updateSQL);
			prpStmt.setString(1, r.getName());
			prpStmt.setString(2, r.getOrganizationId());
			prpStmt.setString(3, r.getId());
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
	public Role findById(String id) {
		 
		Role role = null;
		RowMapper<Role> rowMapper = new RoleRowMapper();

	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByIDSQL);
			prpStmt.setString(1, id);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				role = rowMapper.mapRow(rs, rs.getRow());
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return role;
	}

	@Override
	public List<Role> findByName(String name) {
	 
		List<Role> roles = new ArrayList<Role>();
		RowMapper<Role> rowMapper = new RoleRowMapper();

	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByNameSQL);
			prpStmt.setString(1, name);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				Role role = rowMapper.mapRow(rs, rs.getRow());
				roles.add(role);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return roles;
	}
	
	@Override
	public List<Role> findByOrganization(String orgId) {
	 
		List<Role> roles = new ArrayList<Role>();
		RowMapper<Role> rowMapper = new RoleRowMapper();

	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByOrganizationIDSQL);
			prpStmt.setString(1, orgId);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				Role role = rowMapper.mapRow(rs, rs.getRow());
				roles.add(role);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return roles;
	}
	
}
