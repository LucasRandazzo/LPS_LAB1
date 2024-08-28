package com.lps.back.services.interfaces;

import com.lps.back.models.Teacher;
import com.lps.back.models.Subject;
import java.util.List;

public interface ITeacherService {

    void save(Teacher teacher);

    Teacher get(Long id);

    List<Subject> getSubjects(Long teacherId);

}
