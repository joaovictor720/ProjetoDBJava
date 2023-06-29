package com.backend.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = repo.findAll();
		if (products.size() == 0) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable String name){
		System.out.println("PathVariable = " + name);
		List<Product> products = repo.findByName(name);
		if (products.size() == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(products);
		}
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category){
		System.out.println("PathVariable = " + category);
		List<Product> products = repo.findByCategory(category);
		if (products.size() == 0) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(products);
	}
	
	@PostMapping
	public ResponseEntity<List<Product>> registerProduct( @RequestBody ProductDTO product) {
		List<Product> createdProduct = null;
		try {
			createdProduct = repo.createProduct(
				product.getName(), 
				product.getPrice(), 
				product.getCategory(), 
				product.getColor(), 
				product.getSize(), 
				product.getCount(), 
				product.getCity()
			);
		} catch (DataIntegrityViolationException dive) {
			return ResponseEntity.ok(createdProduct);
		} finally {
			return null;
		}
	}
	
	@PutMapping("/{id}")
	public void updateProduct( @RequestBody ProductDTO product ) {
		
	}
	
}
