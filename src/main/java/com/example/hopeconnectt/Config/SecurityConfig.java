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
    
    UserDetails manager = User.builder()
            .username("manager")
            .password(encoder.encode("manager123"))
            .roles("ORPHANAGE_MANAGER") // Must match exactly
            .build();
    
    return new InMemoryUserDetailsManager(admin, manager);
}


//<<<<<<< HEAD
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
          .csrf(AbstractHttpConfigurer::disable)
          .authorizeHttpRequests(auth -> auth
              // Public endpoints
              .requestMatchers(HttpMethod.POST, "/api/volunteer-matches").permitAll()
              .requestMatchers("/api/auth/**", "/api/donors/**", "/api/reviews/**").permitAll()

            .requestMatchers("/api/emergency-campaigns/**").permitAll()
            .requestMatchers("/api/external-data/**").permitAll()

                
              // Admin endpoints
              .requestMatchers("/api/admin/**", "/api/sponsorships/status/**").hasRole("ADMIN")
              .requestMatchers("/api/donations/approve").hasRole("ADMIN")
              .requestMatchers("/api/donations/**","/api/logistics/**").hasRole("ADMIN")
              .requestMatchers("/api/errors").hasRole("ADMIN")

              
              // Manager endpoints
              .requestMatchers("/api/orphans/**", "/api/orphanages/**").hasRole("ORPHANAGE_MANAGER")
              
              // Combined access
              .requestMatchers("/api/volunteer-matches/**").hasAnyRole("ADMIN", "ORPHANAGE_MANAGER")
              .requestMatchers(HttpMethod.DELETE, "/api/volunteer-matches/**").hasRole("ADMIN")
              .requestMatchers(HttpMethod.DELETE, "/api/reviews/**").hasRole("ADMIN") 
              .requestMatchers( "/api/sponsorships/**").authenticated()
               .requestMatchers( "/api/sponsorships").hasAnyRole("ADMIN")
              .requestMatchers( "/api/sponsorships/**/status").hasRole("ADMIN")
              .anyRequest().authenticated()
          )
          .httpBasic(basic -> basic.realmName("HopeConnect"));
      
      return http.build();
  }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 // Public endpoints
//                 .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                
//                 // Admin-only endpoints
//                 .requestMatchers("/api/auth/admin/**").hasRole("ADMIN")
//                 .requestMatchers("/api/donations/approve").hasRole("ADMIN")
//                 .requestMatchers("/api/orphans/**","/api/orphanages/**","/api/donors/**","/api/donations/**","/api/logistics/**").hasRole("ADMIN")
                
//                 // Orphanage Manager endpoints
//                 // Read-only endpoints (permit all or authenticated)
//                 .requestMatchers(
//                     "/api/orphans",
//                     "/api/orphans/{id}","/api/orphans/by-orphanage/{orphanageId}", 
//                     "/api/orphans/by-age",
//                     "/api/orphans/by-education",
//                     "/api/orphans/by-gender/{gender}"
//                 ).permitAll()
                
//                 // Orphan 
//                 .requestMatchers(
                    
//                     "/api/orphans/delete/{id}",
//                     "/api/orphans/update/{id}"
//                 ).hasRole("ORPHANAGE_MANAGER")
                
//                 // Orphanage 
//                 .requestMatchers("/api/orphanages/update/{id}","/api/orphanages/delete/{id}").hasRole("ORPHANAGE_MANAGER")
//                 .requestMatchers(
//                     "/api/orphanages","/api/orphanages/{id}","/api/orphanages/by-name/{name}","/api/orphanages/by-location/{location}","/api/orphanages/by-verified/{status}"
//                 ).permitAll()
                

//                 // Donor endpoints (public)
//                 //.requestMatchers("/api/donors/**").hasAnyRole("ADMIN")
//                 // sponsorships
//                 .requestMatchers( "/api/sponsorships/**").authenticated()
//                 .requestMatchers( "/api/sponsorships").hasAnyRole("ADMIN")
//                 .requestMatchers( "/api/sponsorships/**/status").hasRole("ADMIN")

                
//                 .anyRequest().authenticated()
//             )
//             .httpBasic(basic -> {});
        
//         return http.build();
//     }
// >>>>>>> may1

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

        
