package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrderUserItem;

@Repository
public interface OrderUserItemRepository extends JpaRepository<OrderUserItem, Long> {
    List<OrderUserItem> findByOrderUserId(Long orderUserId);
}