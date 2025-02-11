package com.example.studentmanagement.repository;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentCode(String studentCode);
    Optional<Student> findByStudentCodeAndDateOfBirth(String studentCode, LocalDate dateOfBirth);
    Collection<Student> findByNameContainingIgnoreCase(String name);
}