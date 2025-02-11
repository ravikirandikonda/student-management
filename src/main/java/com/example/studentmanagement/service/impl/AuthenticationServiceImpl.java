package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.StudentLoginRequest;
import com.example.studentmanagement.dto.AuthResponse;
import com.example.studentmanagement.entity.Admin;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.AdminRepository;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService { // Implement the interface

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse authenticate(StudentLoginRequest loginRequest) {
        // Implement the logic for student authentication
        Optional<Student> student = studentRepository.findByStudentCode(loginRequest.getStudentCode());
        if (student.isPresent() && student.get().getDateOfBirth().equals(loginRequest.getDateOfBirth())) {
            return new AuthResponse("Student login successful!", true);
        }
        return new AuthResponse("Invalid student code or date of birth!", false);
    }

    @Override
    public boolean authenticateAdmin(String username, String password) {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        //return admin.isPresent() && passwordEncoder.matches(password, admin.get().getPassword());
        return admin.isPresent() && admin.get().getPassword().equals(password);
    }
}