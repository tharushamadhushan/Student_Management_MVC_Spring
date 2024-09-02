package com.example.studentmanagement.conversion;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.dto.TeacherDTO;
import com.example.studentmanagement.entity.StudentEntity;
import com.example.studentmanagement.entity.TeacherEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConversionData {
    final private ModelMapper modelMapper;

    public ConversionData(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    public StudentDTO convertToStudentDTO(StudentEntity student){
        return modelMapper.map(student,StudentDTO.class);
    }
    public StudentEntity convertToStudentEntity(StudentDTO studentDTO){
        return modelMapper.map(studentDTO,StudentEntity.class);
    }
    public List<StudentDTO> getStudentDTOList(List<StudentEntity> studentEntityList) {
        return modelMapper.map(studentEntityList,List.class);
    }
    public TeacherDTO convertToTeacherDTO(TeacherEntity teacher){
        return modelMapper.map(teacher,TeacherDTO.class);
    }
    public TeacherEntity convertToTeacherEntity(TeacherDTO teacherDTO){
        return modelMapper.map(teacherDTO,TeacherEntity.class);
    }
    public List<TeacherDTO> getTeacherDTOList(List<TeacherEntity> teacherEntityList) {
        return modelMapper.map(teacherEntityList,List.class);
    }
}
