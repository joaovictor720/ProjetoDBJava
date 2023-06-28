package com.backend.db.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.backend.db.model.Product;

@Repository
public class ProductRepository {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<Product> findAll() {
		return jdbc.query(
			"SELECT * FROM product;",
			new ProductRowMapper()
		);
	}
	
	public List<Product> findByName(String name) {
		return jdbc.query(
			"SELECT * FROM product WHERE name LIKE CONCAT('%', ?, '%');",
			new Object[] { name },
			new ProductRowMapper()
		);
	}
	
	public List<Product> findByCategory(String category) {
		return jdbc.query(
			"SELECT * FROM product WHERE category LIKE CONCAT('%', ?, '%');",
			new Object[] { category },
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
		try {
			jdbc.query(
				"INSERT INTO product (name, price, category, color, size, count, city) " + 
				"VALUES (?, ?, ?, ?, ?, ?, ?);",
				new Object[] { name, price, category, color, size, count, city },
				new ProductRowMapper()
			);
		} catch (DataIntegrityViolationException dive) {
			
		}
	}
	
}
