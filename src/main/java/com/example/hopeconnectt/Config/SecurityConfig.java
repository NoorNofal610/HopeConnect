package com.example.hopeconnectt.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // Admin user
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();
        
        // Orphanage Manager user
        UserDetails manager = User.builder()
                .username("Ali")
                .password(passwordEncoder().encode("321"))
                .roles("ORPHANAGE_MANAGER")
                .build();

        return new InMemoryUserDetailsManager(admin, manager);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                
                // Admin-only endpoints
                .requestMatchers("/api/auth/admin/").hasRole("ADMIN")
            
                .requestMatchers("/api/donations/approve").hasRole("ADMIN")
                
                // Orphanage Manager endpoints
                .requestMatchers("/api/orphans","/api/orphans/{id}","/api/orphans/by-orphanage/{orphanageId}", "/api/orphans/by-age","/api/orphans/by-education","/api/orphans/by-gender/{gender}").permitAll()
                .requestMatchers("/api/orphans/update/{id}","/api/orphans/delete/{id}").hasRole("ORPHANAGE_MANAGER")
                .requestMatchers("/api/orphanage/").hasRole("ORPHANAGE_MANAGER")
                
                // Shared endpoints
               // .requestMatchers("/api/donations/view").hasAnyRole("ADMIN", "ORPHANAGE_MANAGER")
                
                .anyRequest().authenticated()
            )
            .httpBasic(basic -> {});
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}