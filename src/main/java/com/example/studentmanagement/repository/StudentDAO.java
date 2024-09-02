package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface StudentDAO extends JpaRepository<StudentEntity, String>{
}
