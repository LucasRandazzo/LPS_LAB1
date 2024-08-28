package com.lps.back.services.interfaces;

import com.lps.back.models.Student;

import java.util.List;

public interface IStudentService {
    void save(Student student);

    Student get(Long id);

    List<Student> getAll();
}
