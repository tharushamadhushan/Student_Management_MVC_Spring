package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherDAO extends JpaRepository<TeacherEntity, String> {
}
