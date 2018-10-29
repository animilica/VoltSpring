package com.work.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.work.domain.Product;

public class ProductRowMapper implements RowMapper<Product> {
	
	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product prod = new Product();
		prod.setId(String.valueOf(rs.getString("ID")));
		prod.setDescription(String.valueOf(rs.getString("DESCRIPTION")));
		prod.setName(String.valueOf(rs.getString("NAME")));
		prod.setPrice(Double.valueOf(rs.getString("PRICE")));
		prod.setOrganizationId(String.valueOf(rs.getString("ORGANIZATION_ID")));

		return prod;
	}

}