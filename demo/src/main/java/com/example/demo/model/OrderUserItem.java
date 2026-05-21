package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderUserItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderUserId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    private Integer quantity;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private UserProduct product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private OrderUser orderUser;

    public OrderUserItem() {}

    public OrderUserItem(Long orderUserId, Long productId, Integer quantity, Double price) {
        this.orderUserId = orderUserId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

  
    public Long getId() { return id; }
    public Long getOrderUserId() { return orderUserId; }
    public Long getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }
    public Double getPrice() { return price; }
    public UserProduct getProduct() { return product; }
    public OrderUser getOrderUser() { return orderUser; }

   
    public void setId(Long id) { this.id = id; }
    public void setOrderUserId(Long orderUserId) { this.orderUserId = orderUserId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setPrice(Double price) { this.price = price; }
    public void setProduct(UserProduct product) { this.product = product; }
    public void setOrderUser(OrderUser orderUser) { this.orderUser = orderUser; }
}