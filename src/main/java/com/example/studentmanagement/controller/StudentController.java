package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

  

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudentProfile(@PathVariable Long id, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<String>> getEnrolledCourses(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentCourses(id));
    }

    @GetMapping("/{id}/courses/search")
    public ResponseEntity<List<String>> searchAssignedCourses(@PathVariable Long id, @RequestParam String topic) {
        return ResponseEntity.ok(studentService.searchCoursesByTopic(id, topic));
    }

    @DeleteMapping("/{id}/courses/{courseId}")
    public ResponseEntity<String> leaveCourse(@PathVariable Long id, @PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.leaveCourse(id, courseId));
    }
}