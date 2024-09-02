package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.dto.TeacherDTO;
import com.example.studentmanagement.service.TeacherService;
import com.example.studentmanagement.util.UtilMatters;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/v1/teachers")
public class Teacher {
    @Autowired
    TeacherService teacherService;

    @GetMapping("/oops")
    public String healthCheckStudent(){
        return "Health Check Student";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TeacherDTO saveTeacher(@Valid
                                  @RequestPart("firstName") String firstName,
                                  @RequestPart ("lastName") String lastName,
                                  @RequestPart ("address") String address,
                                  @RequestPart ("email") String email,
                                  @RequestPart ("joinDate") String joinDate,
                                  @RequestPart ("salary") String salary,
                                  @RequestPart ("profilePic") String profilePic,
                                  Errors errors){
        if (errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }

        //Build Base64 image
        String base64ProPic = UtilMatters.convertBase64(profilePic);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate parsedDate = LocalDate.parse(joinDate, formatter);

        TeacherDTO buildTeacher = new TeacherDTO();
        buildTeacher.setFirstName(firstName);
        buildTeacher.setLastName(lastName);
        buildTeacher.setAddress(address);
        buildTeacher.setEmail(email);
        buildTeacher.setJoinDate(Date.valueOf(joinDate));
        buildTeacher.setSalary(Double.valueOf(salary));
        buildTeacher.setProfilePic(base64ProPic);

        return teacherService.saveTeacher(buildTeacher);

    }
    @GetMapping(value = "/{id}",produces = "application/json")
    ResponseEntity<TeacherDTO> getSelectedTeacher(@PathVariable ("id") String id){
        TeacherDTO seletedTeacher = teacherService.getSelectedTeacher(id);
        return seletedTeacher != null ? ResponseEntity.ok(seletedTeacher) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping(produces = "application/json")
    List<TeacherDTO> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteTeacher(@PathVariable ("id") String id){
        teacherService.deleteTeacher(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateTeacher(@Valid
                              @RequestPart("firstName") String firstName,
                              @RequestPart ("lastName") String lastName,
                              @RequestPart ("address") String address,
                              @RequestPart ("email") String email,
                              @RequestPart ("joinDate") String joinDate,
                              @RequestPart ("salary") String salary,
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
        TeacherDTO updatedBuildTeacher = new TeacherDTO();
        updatedBuildTeacher.setFirstName(firstName);
        updatedBuildTeacher.setLastName(lastName);
        updatedBuildTeacher.setAddress(address);
        updatedBuildTeacher.setEmail(email);
        updatedBuildTeacher.setJoinDate(Date.valueOf(joinDate));
        updatedBuildTeacher.setSalary(Double.valueOf(salary));
        updatedBuildTeacher.setProfilePic(updatedBase64ProPic);

        teacherService.updateTeacher(id,updatedBuildTeacher);
    }
}
