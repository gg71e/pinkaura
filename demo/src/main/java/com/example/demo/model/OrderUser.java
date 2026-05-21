package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "f_name")
    private String firstName;

    @Column(name = "l_name")
    private String lastName;

    private String phone;
    private String government;
    private String address;

    @Column(name = "total_price")
    private Double totalPrice;

    private String status;

    private LocalDateTime date;

    @Column(name = "payment_method")
    private String paymentMethod;

    @OneToMany(mappedBy = "orderUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderUserItem> items = new ArrayList<>();

    public OrderUser() {}

    public OrderUser(Long userId, String firstName, String lastName, String phone, 
                     String government, String address, Double totalPrice, String paymentMethod) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.government = government;
        this.address = address;
        this.totalPrice = totalPrice;
        this.status = "pending";
        this.date = LocalDateTime.now();
        this.paymentMethod = paymentMethod;
    }

  
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getGovernment() { return government; }
    public String getAddress() { return address; }
    public Double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public LocalDateTime getDate() { return date; }
    public String getPaymentMethod() { return paymentMethod; }
    public List<OrderUserItem> getItems() { return items; }

    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setGovernment(String government) { this.government = government; }
    public void setAddress(String address) { this.address = address; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
    public void setStatus(String status) { this.status = status; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setItems(List<OrderUserItem> items) { this.items = items; }
}