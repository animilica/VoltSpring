package com.work.dao.impl;

import java.nio.charset.StandardCharsets;
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

import com.google_voltpatches.common.hash.Hashing;
import com.work.dao.AdminDAO;
import com.work.dao.OrganizationDAO;
import com.work.domain.Admin;
import com.work.domain.Organization;
import com.work.domain.dto.AdminDTO;
import com.work.mapping.AdminRowMapper;


@Repository
public class AdminDAOImpl implements AdminDAO {
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	private static final String selectAllSQL = "SELECT * FROM ADMIN";
	private static final String insertSQL = "INSERT INTO ADMIN (ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ORGANIZATION_ID) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String insertUsersSQL = "INSERT INTO USERS (USERNAME, PASSWORD, ENABLED) VALUES (?, ?, 1)";
	private static final String updateSQL = "UPDATE ADMIN SET FIRST_NAME = ?, LAST_NAME = ? WHERE ID = ?";
	private static final String updatePasswordSQL = "UPDATE ADMIN SET PASSWORD = ? WHERE ID = ?";
	private static final String updatePasswordUsersSQL = "UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?";
	private static final String deleteSQL = "DELETE FROM ADMIN WHERE ID = ?";
	private static final String deleteUsersSQL = "DELETE FROM USERS WHERE USERNAME = ?";
	private static final String findByFirstNameSQL = "SELECT * FROM ADMIN WHERE FIRST_NAME LIKE ?";
	private static final String findByLastNameSQL = "SELECT * FROM ADMIN WHERE LAST_NAME LIKE ?";
	private static final String findByMailSQL = "SELECT * FROM ADMIN WHERE EMAIL LIKE ?";
	private static final String findByIDSQL = "SELECT * FROM ADMIN WHERE ID = ?";
	private static final String findByOrganizationIDSQL = "SELECT * FROM ADMIN WHERE ORG_ID = ?";
	
	@Value("${spring.datasource.url}")
	private String connectionName;
	
	@Autowired
	private OrganizationDAO orgDAO;
	
	@Override
	public List <Admin> findAll() {
	 
		List<Admin> admins = new ArrayList<Admin>();
		RowMapper<Admin> rowMapper = new AdminRowMapper();

		try {
			Connection conn = DriverManager.getConnection(connectionName);
			Statement query = conn.createStatement();
			ResultSet rs = query.executeQuery(selectAllSQL);
			while (rs.next()) {
				Admin admin = rowMapper.mapRow(rs, rs.getRow());
				admins.add(admin);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return admins;
	}
	
	@Override
	public String save(AdminDTO a) {
		
		
		try {
			Organization org = orgDAO.findById(a.getOrganizationId());
			if (org == null) {
				return "No such organization";
			}
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(insertSQL);
			prpStmt.setString(2, a.getFirstName());
			prpStmt.setString(3, a.getLastName());
			prpStmt.setString(4, a.getEmail());
			String sha256hex = Hashing.sha256()
					  .hashString(a.getPassword(), StandardCharsets.UTF_8)
					  .toString();
			prpStmt.setString(5, sha256hex);
			prpStmt.setString(6, a.getOrganizationId());
			UUID uuid = UUID.randomUUID();
			prpStmt.setString(1, uuid.toString());
			prpStmt.execute();
			PreparedStatement prpStmtUsers = null;
			prpStmtUsers = conn.prepareStatement(insertUsersSQL);
			prpStmtUsers.setString(1, a.getEmail());
			prpStmtUsers.setString(2, sha256hex);
			prpStmtUsers.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}
		return "Success";
	}
	
	@Override
	public String update(AdminDTO a) {
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(updateSQL);
			prpStmt.setString(1, a.getFirstName());
			prpStmt.setString(2, a.getLastName());
			prpStmt.setString(3, a.getId());
			prpStmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}
		return "Success";
	}
	
	@Override
	public String changePassword(AdminDTO a) {
		
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			String sha256hex = Hashing.sha256()
					  .hashString(a.getPassword(), StandardCharsets.UTF_8)
					  .toString();
			prpStmt = conn.prepareStatement(updatePasswordSQL);
			prpStmt.setString(1, sha256hex);
			prpStmt.setString(2, a.getId());
			prpStmt.executeUpdate();
			PreparedStatement prpStmtUsers = null;
			prpStmtUsers = conn.prepareStatement(updatePasswordUsersSQL);
			prpStmtUsers.setString(1, a.getPassword());
			prpStmtUsers.setString(2, a.getEmail());
			prpStmtUsers.executeUpdate();
			
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
			Admin admin = findById(id);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(deleteSQL);
			prpStmt.setString(1, id);
			prpStmt.executeUpdate();
			PreparedStatement prpStmtUsers = null;
			prpStmtUsers = conn.prepareStatement(deleteUsersSQL);
			prpStmtUsers.setString(1, admin.getEmail());
			prpStmtUsers.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}
		
		return "Success";
	}
	
	@Override
	public List<Admin> findByFirstName(String first_name) {
		 
		List<Admin> admins = new ArrayList<Admin>();
		RowMapper<Admin> rowMapper = new AdminRowMapper();

	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByFirstNameSQL);
			prpStmt.setString(1, '%' + first_name +'%');
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				Admin admin = rowMapper.mapRow(rs, rs.getRow());
				admins.add(admin);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return admins;
	}

	@Override
	public List<Admin> findByLastName(String last_name) {
		 
		List<Admin> admins = new ArrayList<Admin>();
		RowMapper<Admin> rowMapper = new AdminRowMapper();
	
		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByLastNameSQL);
			prpStmt.setString(1, '%' + last_name +'%');
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				Admin admin = rowMapper.mapRow(rs, rs.getRow());
				admins.add(admin);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return admins;
	}
	
	@Override
	public Admin findByEmail(String email) {
		 
		Admin admin = null;
		RowMapper<Admin> rowMapper = new AdminRowMapper();

		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByMailSQL);
			prpStmt.setString(1, email);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				admin = rowMapper.mapRow(rs, rs.getRow());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return admin;
	}
	
	
	
	
	@Override
	public Admin findById(String id) {
		 
		Admin admin = null;
		RowMapper<Admin> rowMapper = new AdminRowMapper();

		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByIDSQL);
			prpStmt.setString(1, id);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				admin = rowMapper.mapRow(rs, rs.getRow());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return admin;
	}
	
	@Override
	public Admin findByOrganization(String orgId) {
		 
		Admin admin = null;
		RowMapper<Admin> rowMapper = new AdminRowMapper();

		try {
			Connection conn = DriverManager.getConnection(connectionName);
			PreparedStatement prpStmt = null;
			prpStmt = conn.prepareStatement(findByOrganizationIDSQL);
			prpStmt.setString(1, orgId);
			ResultSet rs = prpStmt.executeQuery();
			while (rs.next()) {
				admin = rowMapper.mapRow(rs, rs.getRow());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		return admin;
	}

}
