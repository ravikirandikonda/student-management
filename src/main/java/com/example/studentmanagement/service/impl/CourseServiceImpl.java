package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.CourseDTO;
import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> new CourseDTO(course.getId(), course.getCourseCode(), 
                        course.getCourseName(), course.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<String> getStudentsInCourse(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        return course.map(c -> c.getStudents().stream()
                .map(student -> student.getName()) // Assuming Student has a `name` field
                .collect(Collectors.toList()))
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }
}