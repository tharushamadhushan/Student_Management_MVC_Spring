package com.example.studentmanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO implements SuperDTO {
//    @NotNull(message = "ID cannot be null")
    private String id;
    @NotNull(message = "First cannot be null")
    private String firstName;
    @NotNull(message = "Last cannot be null")
    private String lastName;
    @NotNull(message = "Level cannot be null")
    private String level;
    private String profilePic;

}
