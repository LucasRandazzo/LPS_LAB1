package com.lps.back.services.interfaces;

import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.models.Student;

import java.util.List;

public interface IStudentService {
    Student create(UserRegisterDTO student);

    Student get(Long id);

    List<Student> getAll();
}
