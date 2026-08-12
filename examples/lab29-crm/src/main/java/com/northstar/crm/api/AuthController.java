package com.northstar.crm.api;

import com.northstar.crm.security.JwtService;
import com.northstar.crm.security.CrmUserDetailsService;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final JwtService jwtService;
  private final CrmUserDetailsService detailsService;
  private final PasswordEncoder passwordEncoder;

  public AuthController(JwtService jwtService, CrmUserDetailsService detailsService, PasswordEncoder passwordEncoder) {
    this.jwtService = jwtService;
    this.detailsService = detailsService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody Map<String, String> body) {
    String username = body.getOrDefault("username", "");
    // Lab users: agent1/agent1 → AGENT; admin1/admin1 → ADMIN
    // TODO: validate credentials via CrmUserDetailsService / PasswordEncoder
    UserDetails user = detailsService.loadUserByUsername(body.get("username"));
    if (!passwordEncoder.matches(body.get("password"), user.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
    }
    String token = jwtService.issueToken(username, username.startsWith("admin") ? "ADMIN" : "AGENT");
    return Map.of("accessToken", token, "tokenType", "Bearer");
  }
}
