package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserSecurity {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
               
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
              
                .requestMatchers(
                    "/login", 
                    "/signup", 
                    "/register", 
                    "/contactus", 
                    "/contact/**", 
                    "/home", 
                    "/products",
                    "/about",
                    "/css/**", 
                    "/js/**", 
                    "/images/**"
                ).permitAll() 
                
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login") 
                
                .successHandler((request, response, authentication) -> {
                    var authorities = authentication.getAuthorities();
                    for (var authority : authorities) {
                       
                        if (authority.getAuthority().equals("ROLE_ADMIN")) {
                            response.sendRedirect("/admin/dashboard");
                            return;
                        }
                    }
                    
                    response.sendRedirect("/products");
                })
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }
}