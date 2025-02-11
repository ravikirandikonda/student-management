package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentLoginRequest;
import com.example.studentmanagement.dto.AuthResponse;

public interface AuthenticationService {
    AuthResponse authenticate(StudentLoginRequest loginRequest);

	boolean authenticateAdmin(String username, String password);
}