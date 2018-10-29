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

import com.work.dao.PermissionDAO;
import com.work.dao.RoleDAO;
import com.work.domain.Entity;
import com.work.domain.Operation;
import com.work.domain.Permission;
import com.work.domain.Role;
import com.work.domain.dto.PermissionDTO;
import com.work.mapping.PermissionRowMapper;

@Repository
public class PermissionDAOImpl implements PermissionDAO {

	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	private static final String selectAllSQL = "SELECT * FROM PERMISSION";
	private static final String insertSQL = "INSERT INTO PERMISSION (ID, ENTITY, OPERATION, ROLE_ID) VALUES (?, ?, ?, ?)";
	private static final String updateSQL = "UPDATE PERMISSION SET ENTITY = ?, OPERATION = ?, ROLE_ID = ? WHERE ID = ?";
	private static final String deleteSQL = "DELETE FROM PERMISSION WHERE ID = ?";
	private static final String findByIDSQL = "SELECT * FROM Permission WHERE ID = ?";
	private static final String findByRoleIDSQL = "SELECT * FROM PERMISSION WHERE ROLE_ID = ?";
	private static final String findByEntitySQL = "SELECT * FROM PERMISSION WHERE ENTITY = ?";
	
	@Value("${spring.datasource.url}")
	private String connectionName;
	
	@Autowired
	private RoleDAO roleDao;
	
	@Override
	public List <Permission> findAll(){

		List <Permission> prms = new ArrayList <Permission>();
		RowMapper<Permission> rowMapper = new PermissionRowMapper();
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			Statement query = conn.createStatement();
			ResultSet rs = query.executeQuery(selectAllSQL);
			while(rs.next()) {
				Permission perm = rowMapper.mapRow(rs, rs.getRow());
				prms.add(perm);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return prms;
	}
	
	@Override
	public String save(PermissionDTO p){
		
		Role role = roleDao.findById(p.getRoleId());
		
		if (role == null) {
			return "No such Role";
		}

		try {
			boolean contains = false;
			for (Entity ent : Entity.values()) {
				if ( ent.name().equals(p.getEntity())) {
					contains = true;
				}
			}
			if (!contains) {
				return "Improper value for Entity";
			}
			contains = false;
			for (Operation opt : Operation.values()) {
				if ( opt.name().equals(p.getOperation())) {
					contains = true;
				}
			}
			if (!contains) {
				return "Improper value for Opeartion";
			}
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(insertSQL);
			prpStmt.setString(2, p.getEntity());
			prpStmt.setString(3, p.getOperation());
			prpStmt.setString(4, p.getRoleId());
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
	public String update(PermissionDTO p) {
		
		Role role = roleDao.findById(p.getRoleId());
		
		if (role == null) {
			return "No such Role";
		}
		
		try {
			boolean contains = false;
			for (Entity ent : Entity.values()) {
				if ( ent.name().equals(p.getEntity())) {
					contains = true;
				}
			}
			if (!contains) {
				return "Improper value for Entity";
			}
			contains = false;
			for (Operation opt : Operation.values()) {
				if ( opt.name().equals(p.getOperation())) {
					contains = true;
				}
			}
			if (!contains) {
				return "Improper value for Opeartion";
			}
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(updateSQL);
			prpStmt.setString(3, p.getRoleId());
			prpStmt.setString(4, p.getId());
			prpStmt.setString(1, p.getEntity());
			prpStmt.setString(2, p.getOperation());
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
	public Permission findById(String id) {
		 
		Permission perm = null;
		RowMapper<Permission> rowMapper = new PermissionRowMapper();
	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByIDSQL);
			prpStmt.setString(1, id);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				perm = rowMapper.mapRow(rs, rs.getRow());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return perm;
	}

	@Override
	public List<Permission> findByRoleId(String id) {
		 
		List<Permission> prms = new ArrayList<Permission>();
		RowMapper<Permission> rowMapper = new PermissionRowMapper();

	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByRoleIDSQL);
			prpStmt.setString(1, id);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				Permission perm = rowMapper.mapRow(rs, rs.getRow());
				prms.add(perm);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return prms;
	}

	@Override
	public List<Permission> findByEntity(String entity) {
		 
		List<Permission> prms = new ArrayList<Permission>();
		RowMapper<Permission> rowMapper = new PermissionRowMapper();
	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByEntitySQL);
			prpStmt.setString(1, entity);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				Permission perm = rowMapper.mapRow(rs, rs.getRow());
				prms.add(perm);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return prms;
	}
}
