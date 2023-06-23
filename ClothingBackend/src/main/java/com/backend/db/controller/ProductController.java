package com.backend.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.db.model.Product;
import com.backend.db.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository repo;
	
	@GetMapping
	public List<Product> getAllProducts(){
		return repo.findAll();
	}
	
	@GetMapping("/name/{name}")
	public List<Product> getProductsByName(@PathVariable String name){
		return repo.findByName(name);
	}
	
	@PostMapping
	public void registerProduct(
			@RequestBody String name, 
			@RequestBody Float price, 
			@RequestBody String category, 
			@RequestBody String color, 
			@RequestBody String size, 
			@RequestBody Integer count, 
			@RequestBody String city
			) {
		repo.createProduct(name, price, category, color, size, count, city);
	}
}
