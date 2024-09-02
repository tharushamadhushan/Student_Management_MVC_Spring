package com.example.studentmanagement.service;

import com.example.studentmanagement.conversion.ConversionData;
import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.entity.StudentEntity;
import com.example.studentmanagement.exception.NotFoundException;
import com.example.studentmanagement.repository.StudentDAO;
import com.example.studentmanagement.util.UtilMatters;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceIMPL implements StudentService{
    @Autowired
    private StudentDAO studentDao;
    @Autowired
    private ConversionData convert;

    @Override
    public StudentDTO saveStudent(StudentDTO student) {
        student.setId(UtilMatters.generateId());
//        StudentEntity studentEntity = convert.convertToStudentEntity(student);
//        studentEntity = studentDao.save(studentEntity);
        return convert.convertToStudentDTO(studentDao.save(convert.convertToStudentEntity(student)));
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        return convert.getStudentDTOList(studentDao.findAll());
    }
    @Override
    public StudentDTO getSelectedStudent(String id) {
        if (!studentDao.existsById(id)) throw new NotFoundException("STUDENT NOT FOUND");
        return convert.convertToStudentDTO(studentDao.getReferenceById(id));
    }

    @Override
    public void deleteStudent(String id) {
        if (!studentDao.existsById(id)) throw new NotFoundException("STUDENT NOT FOUND");
        studentDao.deleteById(id);
    }

    @Override
    public void updateStudent(String id, StudentDTO student) {
        Optional<StudentEntity> tmpStudent = studentDao.findById(id);
        if (!tmpStudent.isPresent()) throw new NotFoundException("STUDENT NOT FOUND");
        tmpStudent.get().setFirstName(student.getFirstName());
        tmpStudent.get().setLastName(student.getLastName());
        tmpStudent.get().setLevel(student.getLevel());
        tmpStudent.get().setProfilePic(student.getProfilePic());
    }
}
