package com.work.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.work.domain.Entity;
import com.work.domain.Operation;
import com.work.domain.Permission;

public class PermissionRowMapper implements RowMapper<Permission> {
	
	@Override
	public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
		Permission perm = new Permission();
		perm.setId(String.valueOf(rs.getString("ID")));
		perm.setEntity(Entity.valueOf(String.valueOf(rs.getString("ENTITY"))));
		perm.setOperation(Operation.valueOf(String.valueOf(rs.getString("OPERATION"))));
		perm.setRoleId(String.valueOf(rs.getString("ROLE_ID")));

		return perm;
	}

}