package com.backend.db.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.backend.db.model.Product;

@Repository
public class ProductRepository {
	
	private JdbcTemplate jdbc;
	
	public List<Product> findAll() {
		return jdbc.query(
				"SELECT * FROM product;",
				new ProductRowMapper()
		);
	}
	
	public List<Product> findByName(String name) {
		return jdbc.query(
			"SELECT * FROM product WHERE name LIKE '%:name%';",
			new Object[] { name },
			new ProductRowMapper()
		);
	}
	
	public void createProduct(
			String name, 
			Float price, 
			String category, 
			String color, 
			String size, 
			Integer count, 
			String city
	) {
		jdbc.query(
			"INSERT INTO product (name, price, category, color, size, count, city) " + 
			"VALUES (':name', :price, ':category', ':color', ':size', :count, ':city') " +
			"RETURNING name, price, category, color, size, count, city;",
			new Object[] { name, price, category, color, size, count, city },
			new ProductRowMapper()
		);
	}
	
}
