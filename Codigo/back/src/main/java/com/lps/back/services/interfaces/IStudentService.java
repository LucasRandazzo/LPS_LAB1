package com.lps.back.services.interfaces;

import java.util.List;

import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.models.Student;

public interface IStudentService {
    Student create(UserRegisterDTO student);

    Student get(Long id);

    List<Student> getAll();

    void delete(Long id);
}
