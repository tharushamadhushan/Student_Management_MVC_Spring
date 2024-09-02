package com.example.studentmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "teacher")
public class TeacherEntity implements SuperEntity{
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Double salary;
    private String address;
    private Date joinDate;
    @Column(columnDefinition = "LONGTEXT")
    private String profilePic;



}
