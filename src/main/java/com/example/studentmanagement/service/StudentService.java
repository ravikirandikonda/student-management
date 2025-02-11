package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.entity.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student registerStudent(Student student);
    List<StudentDTO> getAllStudents();
    Optional<StudentDTO> getStudentById(Long id);
    void enrollStudentInCourse(Long studentId, Long courseId);
    Student updateStudent(Long id, Student student);
    List<StudentDTO> searchStudentsByName(String name);
    List<String> getStudentCourses(Long studentId);
    List<String> searchCoursesByTopic(Long studentId, String topic);
    String leaveCourse(Long studentId, Long courseId);
	boolean validateLogin(String studentCode, String dob);
}