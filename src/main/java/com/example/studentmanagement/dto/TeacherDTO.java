package com.example.studentmanagement.dto;

import com.example.studentmanagement.entity.TeacherEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherDTO implements SuperDTO{
    private String id;
    @NotNull(message = "First cannot be null")
    private String firstName;
    @NotNull(message = "Last cannot be null")
    private String lastName;
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Salary cannot be null")
    private Double salary;
    @NotNull(message = "Address cannot be null")
    private String address;
    @NotNull(message = "Date cannot be null")
    private Date joinDate;
    private String profilePic;

}
