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
import com.backend.db.model.ProductDTO;
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
		System.out.println("PathVariable = " + name);
		return repo.findByName(name);
	}
	
	@GetMapping("/category/{category}")
	public List<Product> getProductsByCategory(@PathVariable String category){
		System.out.println("PathVariable = " + category);
		return repo.findByCategory(category);
	}
	
	@PostMapping
	public void registerProduct( @RequestBody ProductDTO product) {
		repo.createProduct(
			product.getName(), 
			product.getPrice(), 
			product.getCategory(), 
			product.getColor(), 
			product.getSize(), 
			product.getCount(), 
			product.getCity()
		);
	}
}
