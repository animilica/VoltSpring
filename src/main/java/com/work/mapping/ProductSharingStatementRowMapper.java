package com.work.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.work.domain.ProductSharingStatement;
import com.work.domain.Relation;

public class ProductSharingStatementRowMapper implements RowMapper<ProductSharingStatement> {
	
	@Override
	public ProductSharingStatement mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductSharingStatement pss = new ProductSharingStatement();
		pss.setId(String.valueOf(rs.getString("ID")));
		pss.setSharingOrgId(rs.getString("SHARINGORGID"));
		pss.setAccessingOrgId(rs.getString("ACCESSINGORGID"));
		pss.setQuantity(rs.getLong("QUANTITY"));
		pss.setRelation(Relation.valueOf(String.valueOf(rs.getString("RELATION"))));
		int approved = rs.getInt("APPROVED");
		if (approved == 1) {
			pss.setApproved(true);
		} else {
			pss.setApproved(false);
		}
		pss.setPrice(rs.getLong("PRICE"));
		pss.setRoleId(rs.getString("ROLE_ID"));

		return pss;
	}

}