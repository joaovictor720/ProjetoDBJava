package com.backend.db.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.backend.db.model.Product;

public class ProductRowMapper implements RowMapper<Product>{
	
	@Override
	public Product mapRow(ResultSet rs, int rowSum) throws SQLException {
		Product p = new Product();
		p.setId(rs.getLong("id"));
		p.setName(rs.getString("name"));
		p.setPrice(rs.getDouble("price"));
		p.setCategory(rs.getString("category"));
		p.setColor(rs.getString("color"));
		p.setSize(rs.getString("size"));
		p.setCount(rs.getInt("count"));
		p.setCity(rs.getString("city"));
		return p;
	}
	
}
