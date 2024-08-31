package com.lps.back.services.interfaces;

import com.lps.back.models.Teacher;
import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.models.Subject;
import java.util.List;

public interface ITeacherService {

    Teacher create(UserRegisterDTO teacher);

    Teacher get(Long id);

    List<Teacher> getAll();

    List<Subject> getSubjects(Long teacherId);

    void delete(Long id);
}
