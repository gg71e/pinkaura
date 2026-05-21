package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    
    @GetMapping("/login")
    public String loginPage() {
        return "login"; 
    }

   
    @GetMapping("/signup")
    public String signupPage(Model model) { 
        model.addAttribute("user", new User()); 
        return "signup"; 
    }

   
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerNewUser(user); 
        return "redirect:/login";
    }


    @PostMapping("/login/forgot-password")
    public String handlePopupResetPassword(@RequestParam("email") String email, 
                                           @RequestParam("newPassword") String newPassword,
                                           RedirectAttributes redirectAttributes) {
        
       
        boolean isUpdated = userService.updatePasswordByEmail(email, newPassword);
        
        if (!isUpdated) {
            redirectAttributes.addFlashAttribute("error", "Email not found!");
            return "redirect:/login";
        }
        
        redirectAttributes.addFlashAttribute("success", "Password updated successfully!");
        return "redirect:/login";
    }
}