package com.northstar.crm.config;

import com.northstar.crm.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
    http.csrf(csrf -> csrf.disable())
    // TODO: sessionManagement STATELESS
    // TODO: authorizeHttpRequests:
    //   /api/auth/login permitAll
    //   /api/customers/** hasAnyRole("AGENT","ADMIN")
    //   /api/admin/** hasRole("ADMIN")
    //   anyRequest authenticated
    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/auth/login").permitAll()
        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // From the lab guide
        .requestMatchers("/api/customers/**").hasAnyRole("AGENT", "ADMIN")
        .requestMatchers("/api/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated())
    // TODO: addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
