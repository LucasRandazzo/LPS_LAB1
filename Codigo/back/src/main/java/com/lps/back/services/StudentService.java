package com.lps.back.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.models.Student;
import com.lps.back.models.Usuario;
import com.lps.back.repositories.StudentRepository;
import com.lps.back.services.interfaces.IStudentService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserService userService;

    @Override
    public Student create(UserRegisterDTO student) {
        Usuario user = userService.register(student);

        Student newStudent = new Student();
        newStudent.setId(user.getId());
        newStudent.setMail(user.getMail());
        newStudent.setName(user.getName());
        newStudent.setPassword(user.getPassword());

        Student createdObj = this.studentRepository.save(newStudent);
        return createdObj;
    }

    @Override
    public Student get(Long id) {
        Student student = studentRepository.findById(id).get();
        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = studentRepository.findAll();
        if (students == null || students.isEmpty()) {
            throw new EntityNotFoundException("Students not found");
        }
        return students;
    }

}
