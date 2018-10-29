package com.work.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.work.domain.AdminRole;

public class AdminRoleRowMapper implements RowMapper<AdminRole> {
	
	@Override
	public AdminRole mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminRole adminRole = new AdminRole();
		adminRole.setId(String.valueOf(rs.getString("ID")));
		adminRole.setAdminId(String.valueOf(rs.getString("ADMIN_ID")));
		adminRole.setRoleID(String.valueOf(rs.getString("ROLE_ID")));

		return adminRole;
	}

}