package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserProduct;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Long> {
    
    List<UserProduct> findByButtonName(String buttonName);
}