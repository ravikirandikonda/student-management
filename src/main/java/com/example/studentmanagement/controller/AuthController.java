package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.AuthResponse;
import com.example.studentmanagement.dto.StudentLoginRequest;
import com.example.studentmanagement.service.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login/admin")
    public ResponseEntity<String> adminLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if (authenticationService.authenticateAdmin(username, password)) {
            session.setAttribute("user", username);
            session.setAttribute("role", "ADMIN");
            return ResponseEntity.ok("Admin login successful!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid admin credentials!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginStudent(@RequestBody StudentLoginRequest loginRequest) {
        AuthResponse response = authenticationService.authenticate(loginRequest);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully!");
    }
}