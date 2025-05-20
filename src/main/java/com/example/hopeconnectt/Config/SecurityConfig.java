package com.example.hopeconnectt.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
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
public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    UserDetails admin = User.builder()
            .username("admin")
            .password(encoder.encode("admin123"))
            .roles("ADMIN") // Must match hasRole("ADMIN")
            .build();
    UserDetails donar = User.builder()
            .username("donar")
            .password(encoder.encode("donar123"))
            .roles("DONOR") // Must match hasRole("DONOR")
            .build();
            UserDetails volnter = User.builder()
            .username("volnter")
            .password(encoder.encode("volnter123"))
            .roles("VOLUNTEER") // Must match hasRole("VOLUNTEER")
            .build();
    UserDetails manager = User.builder()
            .username("manager")
            .password(encoder.encode("manager123"))
            .roles("ORPHANAGE_MANAGER") // Must match exactly
            .build();
    
    return new InMemoryUserDetailsManager(admin, manager,donar,volnter);
}


//<<<<<<< HEAD
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
          .csrf(AbstractHttpConfigurer::disable)
          .authorizeHttpRequests(auth -> auth
              // Public endpoints
              .requestMatchers("/api/auth/**").hasRole("ADMIN")

            .requestMatchers("/api/external-data/**").hasRole("ADMIN")

        .requestMatchers("/api/donors/**").hasAnyRole("DONOR", "ADMIN")

              // Admin endpoints
                          .requestMatchers("/api/emergency-campaigns/**").permitAll()

              .requestMatchers("/api/admin/**", "/api/sponsorships/status/**").hasRole("ADMIN")
              .requestMatchers("/api/donations/approve").hasRole("ADMIN")
              .requestMatchers("/api/donations/**","/api/logistics/**").hasRole("ADMIN")
              .requestMatchers("/api/errors").hasRole("ADMIN")
              
              // Manager endpoints
              .requestMatchers("/api/orphans/**", "/api/orphanages/**","/api/volunteer-matches/**").hasAnyRole("ORPHANAGE_MANAGER","ADMIN")
              
              // Combined access
              .requestMatchers("/api/volunteers/**","/api/volunteer-matches/**").hasAnyRole("ADMIN", "VOLUNTEER")
        //.requestMatchers( "/api/reviews/**").hasRole("ADMIN") 
              .requestMatchers( "/api/sponsorships/**","/api/reviews/**").hasAnyRole("DONOR", "ADMIN")
              .anyRequest().authenticated()
          )
          .httpBasic(basic -> basic.realmName("HopeConnect"));
      
      return http.build();
  }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

        
