package com.example.studentmanagement.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private String courseCode;
    private String courseName;
    private String description;
    
    public CourseDTO(Long id, String courseCode, String courseName, String description) {
        this.setId(id);
        this.setCourseCode(courseCode);
        this.setCourseName(courseName);
        this.setDescription(description);
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}