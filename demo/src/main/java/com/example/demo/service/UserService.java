package com.example.demo.service;

import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    public void registerNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole("USER"); 
        }
        userRepository.save(user);
    }

    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

   
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
  
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

   
    @Transactional 
    public boolean updatePasswordByEmail(String email, String newPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return false;
        }
        
        User user = userOpt.get();
       
        user.setPassword(passwordEncoder.encode(newPassword));
        
     
        userRepository.saveAndFlush(user); 
        return true;
    }
}