package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // <--- لازم السطر ده يكون موجود
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // صفحة اللوجن
    @GetMapping("/login")
    public String loginPage() {
        return "login"; 
    }

    // صفحة السين اب (بعد التعديل)
    @GetMapping("/signup")
    public String signupPage(Model model) { 
        // بنجهز كائن يوزر فاضي عشان الفورم في Thymeleaf تملاه
        model.addAttribute("user", new User()); 
        return "signup"; 
    }

    // عملية التسجيل الفعلية
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        // بنبعت اليوزر للسيرفيس وهي تشفر وتحفظ
        userService.registerNewUser(user); 
        
        // بعد النجاح بنوديه لصفحة اللوجن
        return "redirect:/login";
    }
}