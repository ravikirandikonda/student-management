package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.CourseDTO;
import com.example.studentmanagement.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course createCourse(Course course);
    List<CourseDTO> getAllCourses();
	Optional<Course> getCourseById(Long id);
	List<String> getStudentsInCourse(Long courseId);
}