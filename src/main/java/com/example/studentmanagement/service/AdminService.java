package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Admin;

public interface AdminService {
    Admin registerAdmin(Admin admin);
    String loginAdmin(String username, String password);
}