package com.example.userregistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userregistration.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
