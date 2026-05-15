package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);
    
    if (user == null) {
        throw new UsernameNotFoundException("User not found with email: " + email);
    }

    // تأكدي إن الـ Role اللي جاية من الـ DB لو هي "ADMIN" تتحول لـ "ROLE_ADMIN"
    String role = user.getRole();
    if (!role.startsWith("ROLE_")) {
        role = "ROLE_" + role;
    }

    return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password(user.getPassword())
            .authorities(role) 
            .build();
}}