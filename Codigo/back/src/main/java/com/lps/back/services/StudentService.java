package com.lps.back.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.back.models.Student;
import com.lps.back.repositories.StudentRepository;
import com.lps.back.services.interfaces.IStudentService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void save(Student student) {
        studentRepository.save(student);
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
