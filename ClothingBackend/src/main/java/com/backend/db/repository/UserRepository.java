package com.backend.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.db.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
