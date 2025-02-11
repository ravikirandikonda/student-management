package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.service.StudentService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> new StudentDTO(
                        student.getId(),
                        student.getStudentCode(),  // Updated
                        student.getName(),
                        student.getEmail(),
                        student.getDateOfBirth()  // Updated
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDTO> getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(student -> new StudentDTO(
                        student.getId(),
                        student.getStudentCode(),  // Updated
                        student.getName(),
                        student.getEmail(),
                        student.getDateOfBirth()  // Updated
                ));
    }

    @Override
    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        if (!student.getCourses().contains(course)) {
            student.getCourses().add(course);
            studentRepository.save(student);
        }
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id).map(student -> {
            if (updatedStudent.getEmail() != null) student.setEmail(updatedStudent.getEmail());
            if (updatedStudent.getMobile() != null) student.setMobile(updatedStudent.getMobile());
            if (updatedStudent.getParentsName() != null) student.setParentsName(updatedStudent.getParentsName());
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<String> getStudentCourses(Long studentId) {
        return studentRepository.findById(studentId)
                .map(student -> student.getCourses()
                        .stream()
                        .map(Course::getCourseName)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<String> searchCoursesByTopic(Long studentId, String topic) {
        return studentRepository.findById(studentId)
                .map(student -> student.getCourses()
                        .stream()
                        .filter(course -> course.getDescription().toLowerCase().contains(topic.toLowerCase()))
                        .map(Course::getCourseName)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<StudentDTO> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(student -> new StudentDTO(
                        student.getId(),
                        student.getStudentCode(),
                        student.getName(),
                        student.getEmail(),
                        student.getDateOfBirth()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public String leaveCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        if (student.getCourses().contains(course)) {
            student.getCourses().remove(course);
            studentRepository.save(student);
            return "Successfully left the course";
        }
        return "Student is not enrolled in this course";
    }

	@Override
	public boolean validateLogin(String studentCode, String dob) {
		// TODO Auto-generated method stub
		return false;
	}
}