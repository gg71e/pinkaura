package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
private PasswordEncoder passwordEncoder;

public void registerNewUser(User user) {
    // تشفير الباسورد قبل الحفظ
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    // ضبط الـ Role الافتراضي لو مش موجود
    if (user.getRole() == null) {
        user.setRole("USER"); 
    }
    userRepository.save(user);
}

    // 2. ميثود بتجيب كل اليوزرز (عشان تظهر في جدول الأدمن)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 3. ميثود لحذف يوزر (عشان زرار Delete اللي في الـ HTML يشتغل)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    // 4. ميثود للبحث عن يوزر بالإيميل (بتحتاجيها في الـ Security والـ Login)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}