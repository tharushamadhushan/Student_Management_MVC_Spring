package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO saveStudent(StudentDTO student);
    List<StudentDTO> getAllStudent();
    StudentDTO getSelectedStudent(String id);
    void deleteStudent(String id);
    void updateStudent(String id, StudentDTO student);
}
