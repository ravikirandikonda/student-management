package com.example.studentmanagement.dto;

import java.time.LocalDate;

import lombok.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String studentCode;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    public StudentDTO(Long id, String studentCode, String name, String email, LocalDate localDate) {
        this.id = id;
        this.studentCode = studentCode;
        this.name = name;
        this.email = email;
        this.dateOfBirth = localDate;
    }


	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}