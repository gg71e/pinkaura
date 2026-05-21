package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OrderUser;
import com.example.demo.model.OrderUserItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderUserItemRepository;
import com.example.demo.repository.OrderUserRepository;

@RestController
@RequestMapping("/api/orders")
public class OrderUserController {

    @Autowired
    private OrderUserRepository orderUserRepository;

    @Autowired
    private OrderUserItemRepository orderUserItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    
    

    @PostMapping("/place")
    public ResponseEntity<Map<String, String>> placeOrder(@RequestBody Map<String, Object> orderData) {
        Map<String, String> response = new HashMap<>();
        
        try {
            System.out.println("Received order data: " + orderData);
            
            Long userId = orderData.get("userId") != null ? Long.valueOf(orderData.get("userId").toString()) : null;
            String firstName = orderData.get("firstName").toString();
            String lastName = orderData.get("lastName").toString();
            String phone = orderData.get("phone").toString();
            String government = orderData.get("government").toString();
            String address = orderData.get("address").toString();
            String paymentMethod = orderData.get("paymentMethod").toString();
            Double totalPrice = Double.valueOf(orderData.get("totalAmount").toString());
            
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> items = (List<Map<String, Object>>) orderData.get("items");
            
            OrderUser order = new OrderUser();
            order.setUserId(userId);
            order.setFirstName(firstName);
            order.setLastName(lastName);
            order.setPhone(phone);
            order.setGovernment(government);
            order.setAddress(address);
            order.setTotalPrice(totalPrice);
            order.setStatus("pending");
            order.setDate(LocalDateTime.now());
            order.setPaymentMethod(paymentMethod);
            
            OrderUser savedOrder = orderUserRepository.save(order);
            System.out.println("Order saved with ID: " + savedOrder.getId());
            
            if (items != null && !items.isEmpty()) {
                for (Map<String, Object> item : items) {
                    Long productId = Long.valueOf(item.get("product_id").toString());
                    Integer quantity = Integer.valueOf(item.get("quantity").toString());
                    Double price = Double.valueOf(item.get("price").toString());
                    
                    OrderUserItem orderItem = new OrderUserItem();
                    orderItem.setOrderUserId(savedOrder.getId());
                    orderItem.setProductId(productId);
                    orderItem.setQuantity(quantity);
                    orderItem.setPrice(price);
                    orderUserItemRepository.save(orderItem);
                    System.out.println("Order item saved for product: " + productId);
                }
            }
            
            if (userId != null) {
                cartRepository.findByUserId(userId).ifPresent(cart -> {
                    cartItemRepository.deleteByCartId(cart.getId());
                    cartRepository.delete(cart);
                    System.out.println("Cart cleared for user: " + userId);
                });
            }
            
            response.put("status", "success");
            response.put("message", "Order placed successfully!");
            response.put("orderId", savedOrder.getId().toString());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderUser>> getUserOrders(@PathVariable Long userId) {
        try {
            List<OrderUser> orders = orderUserRepository.findByUserId(userId);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<OrderUserItem>> getOrderItems(@PathVariable Long orderId) {
        try {
            List<OrderUserItem> items = orderUserItemRepository.findByOrderUserId(orderId);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderUser> getOrder(@PathVariable Long orderId) {
        try {
            OrderUser order = orderUserRepository.findById(orderId).orElse(null);
            if (order != null) {
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}