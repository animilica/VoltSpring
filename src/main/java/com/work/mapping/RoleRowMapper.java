package com.work.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.work.domain.Role;

public class RoleRowMapper implements RowMapper<Role> {
	
	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role = new Role();
		role.setId(String.valueOf(rs.getString("ID")));
		role.setName(String.valueOf(rs.getString("NAME")));
		role.setOrganizationId(String.valueOf(rs.getString("ORGANIZATION_ID")));

		return role;
	}

}