package com.example.demo.Controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entitys.User;
import com.example.demo.Services.AuthService;
import com.example.demo.dto.LoginRequest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:5174" , allowCredentials = "true")
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            // Authenticate and get JWT token
            String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

            // Get user details
            User user = authService.getUserByUsername(loginRequest.getUsername());

            // Create HTTP-only cookie
            jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("authToken", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(false);  // true if using HTTPS
            cookie.setPath("/");
            cookie.setMaxAge(3600);   // 1 hour
            response.addCookie(cookie);

            // Response body (optional)
            Map<String, Object> responseBody = Map.of(
                "message", "Login successful",
                "token", token,
                "username", user.getUsername(),
                "email", user.getEmail(),
                "role", user.getRole().name()
            );

            return ResponseEntity.ok(responseBody);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Retrieve authenticated user from the request
            User user = (User) request.getAttribute("authenticatedUser");

            // Delegate logout operation to the service layer
            authService.logout(user);

            // Clear the authentication token cookie
            Cookie cookie = new Cookie("authToken", null);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);

            // Success response
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Logout successful");
            return ResponseEntity.ok(responseBody);
        } catch (RuntimeException e) {
            // Error response
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Logout failed");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

}
