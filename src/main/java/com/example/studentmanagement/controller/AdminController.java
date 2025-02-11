package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.CourseService;
import com.example.studentmanagement.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AdminController {
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    private boolean isAdmin(HttpSession session) {
        return "ADMIN".equals(session.getAttribute("role"));
    }

    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@RequestBody Student student, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).body("Access Denied!");
        studentService.registerStudent(student);
        return ResponseEntity.ok("Student added successfully!");
    }

    @PutMapping("/admin/students/{id}") // Changed URL
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).body("Access Denied!");
        studentService.updateStudent(id, student);
        return ResponseEntity.ok("Student updated successfully!");
    }

    @GetMapping("/students")
    public ResponseEntity<?> searchStudents(@RequestParam String name, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).body("Access Denied!");
        return ResponseEntity.ok(studentService.searchStudentsByName(name)); // Corrected
    }

    @PostMapping("/courses")
    public ResponseEntity<String> createCourse(@RequestBody Course course, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).body("Access Denied!");
        courseService.createCourse(course);
        return ResponseEntity.ok("Course created successfully!");
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<?> getCourseDetails(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).body("Access Denied!");
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/courses/{id}/students")
    public ResponseEntity<?> getStudentsInCourse(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).body("Access Denied!");
        return ResponseEntity.ok(courseService.getStudentsInCourse(id));
    }

    @PostMapping("/courses/{courseId}/assign/{studentId}")
    public ResponseEntity<String> assignStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId, HttpSession session) {
        if (!isAdmin(session)) return ResponseEntity.status(403).body("Access Denied!");
        studentService.enrollStudentInCourse(studentId, courseId);
        return ResponseEntity.ok("Student assigned to course successfully");
    }
}