package com.example.studentmanagement.security;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String studentCode = authentication.getName();
        String dateOfBirthStr = authentication.getCredentials().toString();

        // Convert String to LocalDate
        LocalDate dateOfBirth;
        try {
            dateOfBirth = LocalDate.parse(dateOfBirthStr);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid date format. Expected format: YYYY-MM-DD");
        }

        Optional<Student> studentOptional = studentRepository.findByStudentCodeAndDateOfBirth(studentCode, dateOfBirth);
        if (studentOptional.isEmpty()) {
            throw new BadCredentialsException("Invalid Student Code or Date of Birth");
        }

        Student student = studentOptional.get();
        return new UsernamePasswordAuthenticationToken(studentCode, dateOfBirth, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}