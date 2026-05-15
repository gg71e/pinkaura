package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.ContactUs;
import com.example.demo.repository.ContactUsRepository;

@Controller
public class ContactController {

    @Autowired
    private ContactUsRepository contactUsRepository;

    @GetMapping("/contactus")
    public String showContactPage() {
        return "contactus";
    }

    @PostMapping("/contact/send")
    public String sendMessage(@ModelAttribute ContactUs contactUs) {
        contactUsRepository.save(contactUs); 
        return "redirect:/contactus?status=success"; 
    }
}