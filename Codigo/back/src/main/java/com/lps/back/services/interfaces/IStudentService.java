package com.lps.back.services.interfaces;

import com.lps.back.models.Student;

import java.util.List;

public interface IStudentService {
    public void save(Student student);

    public Student get(Long id);

    public List<Student> getAll();
}
