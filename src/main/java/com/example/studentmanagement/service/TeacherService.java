package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {
    TeacherDTO saveTeacher(TeacherDTO teacher);
    List<TeacherDTO> getAllTeacher();
    TeacherDTO getSelectedTeacher(String id);
    void deleteTeacher(String id);
    void updateTeacher(String id, TeacherDTO teacher);
}
