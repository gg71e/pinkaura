package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.UserProduct;
import com.example.demo.service.UserProductService;

@Controller
public class ProductController {

    @Autowired
    private UserProductService userProductService;

    @GetMapping("/cleanser")
    public String getCleansersPage(Model model) {
        List<UserProduct> products = userProductService.getProductsByButtonName("Cleansers");
        model.addAttribute("products", products);
        return "cleanser";
    }
    
    @GetMapping("/serum")
    public String getSerumPage(Model model) {
        List<UserProduct> products = userProductService.getProductsByButtonName("Serum");
        model.addAttribute("products", products);
        return "serum";
    }
    
    @GetMapping("/sunscreen")
    public String getSunscreenPage(Model model) {
        List<UserProduct> products = userProductService.getProductsByButtonName("Sunscreen");
        model.addAttribute("products", products);
        return "sunscreen";
    }
    
    @GetMapping("/moisturizer")
    public String getMoisturizerPage(Model model) {
        List<UserProduct> products = userProductService.getProductsByButtonName("Moisturizer");
        model.addAttribute("products", products);
        return "moisturizer";
    }
    
    
    @GetMapping("/lipstick")
    public String getLipstickPage(Model model) {
        List<UserProduct> products = userProductService.getProductsByButtonName("Lipstick");
        model.addAttribute("products", products);
        return "lipstick";
    }
    
    @GetMapping("/foundation")
    public String getFoundationPage(Model model) {
        List<UserProduct> products = userProductService.getProductsByButtonName("Foundation");
        model.addAttribute("products", products);
        return "foundation";
    }
    
    @GetMapping("/blush")
    public String getBlushPage(Model model) {
        List<UserProduct> products = userProductService.getProductsByButtonName("Blush");
        model.addAttribute("products", products);
        return "blush";
    }
    
    @GetMapping("/mascara")
    public String getMascaraPage(Model model) {
        List<UserProduct> products = userProductService.getProductsByButtonName("Mascara");
        model.addAttribute("products", products);
        return "mascara";
    }
    
   
    
    @GetMapping("/home")
    public String home() { 
        return "home"; 
    }
    
    @GetMapping("/about")
    public String about() { 
        return "about"; 
    }
    
    @GetMapping("/contact")
    public String contact() { 
        return "contactus"; 
    }
    
    @GetMapping("/products")
    public String products() { 
        return "products"; 
    }
    
    @GetMapping("/checkout")
    public String checkout() { 
        return "checkout"; 
    }
   
}