package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String product_name;
    private double price;
    private String image;
    private String button_name;

    public Product() {}

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getProduct_name() { return product_name; }
    public void setProduct_name(String product_name) { this.product_name = product_name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getButton_name() { return button_name; }
    public void setButton_name(String button_name) { this.button_name = button_name; }
}