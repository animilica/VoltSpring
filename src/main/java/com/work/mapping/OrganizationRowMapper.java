package com.work.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.work.domain.Organization;

public class OrganizationRowMapper implements RowMapper<Organization> {
	
	@Override
	public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
		Organization org = new Organization();
		org.setId(String.valueOf(rs.getString("ID")));
		int master = Integer.parseInt(String.valueOf(rs.getString("MASTER")));
		if (master == 0) {
			org.setMaster(false);
		} else {
			org.setMaster(true);
		}
		org.setName(String.valueOf(rs.getString("NAME")));

		return org;
	}

}