package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserProduct;
import com.example.demo.repository.UserProductRepository;

@Service
public class UserProductService {

    @Autowired
    private UserProductRepository userProductRepository;

    public List<UserProduct> getProductsByButtonName(String buttonName) {
        return userProductRepository.findByButtonName(buttonName);
    }
    
    public List<UserProduct> getAllProducts() {
        return userProductRepository.findAll();
    }
}