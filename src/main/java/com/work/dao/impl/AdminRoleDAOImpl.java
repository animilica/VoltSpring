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
import com.work.dao.RoleDAO;
import com.work.domain.Admin;
import com.work.domain.AdminRole;
import com.work.domain.Role;
import com.work.domain.dto.AdminRoleDTO;
import com.work.mapping.AdminRoleRowMapper;


@Repository
public class AdminRoleDAOImpl implements AdminRoleDAO {
	
	private static final String selectAllSQL = "SELECT * FROM ADMINROLE";
	private static final String insertSQL = "INSERT INTO ADMINROLE (ID, ADMIN_ID, ROLE_ID) VALUES (?, ?, ?)";
	private static final String updateSQL = "UPDATE ADMINROLE SET ADMIN_ID = ?, ROLE_ID = ? WHERE ID = ?";
	private static final String deleteSQL = "DELETE FROM ADMINROLE WHERE ID = ?";
	private static final String findByIDSQL = "SELECT * FROM ADMINROLE WHERE ID = ?";
	private static final String findByAdminIDSQL = "SELECT * FROM ADMINROLE WHERE ADMIN_ID = ?";
	private static final String findByRoleIDSQL = "SELECT * FROM ADMINROLE WHERE ROLE_ID = ?";

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	@Value("${spring.datasource.url}")
	private String connectionName;
	
	@Autowired
	private AdminDAO adminDao;
	
	@Autowired
	private RoleDAO roleDao;
	
	@Override
	public List<AdminRole> findAll(){
		
		String sql = "SELECT * FROM ADMINROLE";
		List <AdminRole> ars = new ArrayList <AdminRole>();
		RowMapper<AdminRole> rowMapper = new AdminRoleRowMapper();
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			Statement query = conn.createStatement();
			ResultSet rs = query.executeQuery(selectAllSQL);
			while(rs.next()) {
				AdminRole ar = rowMapper.mapRow(rs, rs.getRow());
				ars.add(ar);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return ars;
	}
	
	@Override
	public String save(AdminRoleDTO ar){
		
		Admin admin = adminDao.findById(ar.getAdminId());
		
		Role role = roleDao.findById(ar.getRoleId());
		
		if (role == null) {
			return "No such Role!";
		}
		
		if (admin == null) {
			return "No such Admin!";
		}
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(insertSQL);
			prpStmt.setString(2, ar.getAdminId());
			prpStmt.setString(3, ar.getRoleId());
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
	public String update(AdminRoleDTO ar){
		
		Admin admin = adminDao.findById(ar.getAdminId());
		
		Role role = roleDao.findById(ar.getRoleId());
		
		if (role == null) {
			return "No such Role!";
		}
		
		if (admin == null) {
			return "No such Admin!";
		}
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(updateSQL);
			prpStmt.setString(2, ar.getRoleId());
			prpStmt.setString(3, ar.getId());
			prpStmt.setString(1, ar.getAdminId());
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
	
	
	public AdminRole findById(String id) {
		 
		AdminRole ar = null;
		RowMapper<AdminRole> rowMapper = new AdminRoleRowMapper();
	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByIDSQL);
			prpStmt.setString(1, id);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				ar = rowMapper.mapRow(rs, rs.getRow());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return ar;
	}
	
	
	public List<AdminRole> findByAdminId(String adminId) {
		 
		AdminRole ar = null;
		List<AdminRole> ars = new ArrayList<AdminRole>();
		RowMapper<AdminRole> rowMapper = new AdminRoleRowMapper();
	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByAdminIDSQL);
			prpStmt.setString(1, adminId);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				ar = rowMapper.mapRow(rs, rs.getRow());
				ars.add(ar);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return ars;
	}
	
	public AdminRole findByRoleId(String roleId) {
		 
		AdminRole ar = null;
		RowMapper<AdminRole> rowMapper = new AdminRoleRowMapper();
	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByRoleIDSQL);
			prpStmt.setString(1, roleId);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				ar = rowMapper.mapRow(rs, rs.getRow());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return ar;
	}
}
