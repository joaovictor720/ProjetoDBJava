package com.backend.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.db.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
