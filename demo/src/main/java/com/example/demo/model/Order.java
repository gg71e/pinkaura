package com.example.demo.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer user_id;

    @Column(name = "F_name")
    private String f_name;

    @Column(name = "L_name")
    private String l_name;

    private String phone;
    private String government;
    private String address;
    private Double total_price;
    private String status = "pending";

   @Column(name = "date", insertable = false, updatable = false)
    private LocalDateTime date;

    private String payment_method;

    public Order() {}

  
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getUser_id() { return user_id; }
    public void setUser_id(Integer user_id) { this.user_id = user_id; }

    public String getF_name() { return f_name; }
    public void setF_name(String f_name) { this.f_name = f_name; }

    public String getL_name() { return l_name; }
    public void setL_name(String l_name) { this.l_name = l_name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGovernment() { return government; }
    public void setGovernment(String government) { this.government = government; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Double getTotal_price() { return total_price; }
    public void setTotal_price(Double total_price) { this.total_price = total_price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public String getPayment_method() { return payment_method; }
    public void setPayment_method(String payment_method) { this.payment_method = payment_method; }
}