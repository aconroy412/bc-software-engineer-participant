package com.northstar.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.northstar.crm.security.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody Map<String, String> body
    ) {
        String username = body.getOrDefault("username", "");
        String password = body.getOrDefault("password", "");

        String role;

        if (username.equals("agent1") && password.equals("agent1")) {
            role = "AGENT";
        } else if (username.equals("admin1") && password.equals("admin1")) {
            role = "ADMIN";
        } else {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Bad credentials"
            );
        }

        String token = jwtService.issueToken(username, role);

        return Map.of(
                "accessToken", token,
                "tokenType", "Bearer"
        );
    }
}