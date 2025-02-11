package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT s.name FROM Student s JOIN s.courses c WHERE c.id = :courseId")
    List<String> findStudentNamesByCourseId(@Param("courseId") Long courseId);
}