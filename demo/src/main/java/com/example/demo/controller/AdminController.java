package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.ContactUsRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ContactUsRepository contactUsRepository;

   
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("usersCount", userService.getAllUsers().size());
        model.addAttribute("productsCount", productRepository.count());
        model.addAttribute("ordersCount", orderRepository.count());
        
        double totalRevenue = orderRepository.findAll()
                .stream()
                .mapToDouble(Order::getTotal_price) 
                .sum();
        model.addAttribute("revenue", totalRevenue);
        
        return "admin/dashboard";
    }

    
    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("usersList", userService.getAllUsers());
        return "admin/useradmin"; 
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id); 
        return "redirect:/admin/users";
    }

    
    @GetMapping("/products")
    public String manageProducts(Model model) {
        model.addAttribute("productsList", productRepository.findAll());
        return "admin/crud"; 
    }

   
    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin/products";
    }

    
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/products";
    }

  
    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "admin/edit-product"; 
    }

    
    @PostMapping("/products/update")
    public String updateProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin/products";
    }

    
    @GetMapping("/orders")
    public String manageOrders(Model model) {
        model.addAttribute("ordersList", orderRepository.findAll());
        return "admin/orders";
    }

    
    @GetMapping("/orders/cancel/{id}")
    public String cancelOrder(@PathVariable("id") Long id) {
        System.out.println("Attempting to delete order with ID: " + id);
        try {
            orderRepository.deleteById(id);
            System.out.println("Order deleted successfully!");
        } catch (Exception e) {
            System.out.println("Error deleting order: " + e.getMessage());
        }
        return "redirect:/admin/orders";
    }

    
    @GetMapping("/messages")
    public String manageMessages(Model model) {
        model.addAttribute("messagesList", contactUsRepository.findAll());
        return "admin/messages";
    }

    @GetMapping("/messages/delete/{id}")
    public String deleteMessage(@PathVariable("id") Long id) {
        contactUsRepository.deleteById(id);
        return "redirect:/admin/messages";
    }

    @PostMapping("/messages/reply")
    public String replyMessage(@RequestParam("replyText") String replyText, 
                               @RequestParam("messageId") Long messageId) {
        System.out.println("Reply to msg ID " + messageId + ": " + replyText);
        return "redirect:/admin/messages?success=true";
    }
}