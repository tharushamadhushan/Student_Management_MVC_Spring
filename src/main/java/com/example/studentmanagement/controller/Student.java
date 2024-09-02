package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.service.StudentService;
import com.example.studentmanagement.util.UtilMatters;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class Student {
    @Autowired
    StudentService studentService;

    @GetMapping("/health")
    public String healthCheckStudent(){
        return "Health Check Student";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public StudentDTO saveStudent(@Valid
                                @RequestPart ("firstName") String firstName,
                                @RequestPart ("lastName") String lastName,
                                @RequestPart ("level") String level,
                                @RequestPart ("profilePic") String profilePic,
                            Errors errors){
        if (errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }

        //Build Base64 image
        String base64ProPic = UtilMatters.convertBase64(profilePic);

        StudentDTO buildStudent = new StudentDTO();
        buildStudent.setFirstName(firstName);
        buildStudent.setLastName(lastName);
        buildStudent.setLevel(level);
        buildStudent.setProfilePic(base64ProPic);

        return studentService.saveStudent(buildStudent);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    ResponseEntity<StudentDTO> getSelectedStudent(@PathVariable ("id") String id){
        StudentDTO seletedStudent = studentService.getSelectedStudent(id);
        return seletedStudent != null ? ResponseEntity.ok(seletedStudent) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping(produces = "application/json")
    List<StudentDTO> getAllStudent(){
        return studentService.getAllStudent();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteStudent(@PathVariable ("id") String id){
        studentService.deleteStudent(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PatchMapping(value = "/{id}")
//    public void upateStudent(@Valid @RequestBody StudentDTO student, @PathVariable("id")String id,Errors errors){
//        studentService.updateStudent(id, student);
//    }
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateStudent(@Valid
                              @RequestPart ("firstName") String firstName,
                              @RequestPart ("lastName") String lastName,
                              @RequestPart ("level") String level,
                              @RequestPart ("profilePic") String profilePic,
                              @RequestParam ("id") String id,
                              Errors errors){
        if(errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }
        //Build Base64 image
        String updatedBase64ProPic = UtilMatters.convertBase64(profilePic);
        //build object
        StudentDTO updatedBuildStudent = new StudentDTO();
        updatedBuildStudent.setFirstName(firstName);
        updatedBuildStudent.setLastName(lastName);
        updatedBuildStudent.setLevel(level);
        updatedBuildStudent.setProfilePic(updatedBase64ProPic);

        studentService.updateStudent(id,updatedBuildStudent);
    }
}
