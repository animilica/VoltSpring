package com.work.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.work.domain.Admin;

public class AdminRowMapper implements RowMapper<Admin> {
	
	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin = new Admin();
		admin.setId(String.valueOf(rs.getString("ID")));
		admin.setFirstName(String.valueOf(rs.getString("FIRST_NAME")));
		admin.setLastName(String.valueOf(rs.getString("LAST_NAME")));
		admin.setEmail(String.valueOf(rs.getString("EMAIL")));
		admin.setOrganizationId(String.valueOf(rs.getString("ORGANIZATION_ID")));
		admin.setPassword(String.valueOf(rs.getString("PASSWORD")));

		return admin;
	}

}
