package com.lps.back.services.interfaces;

import com.lps.back.models.Teacher;
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import java.util.List;

public interface TeacherServiceInterface {

    public void save(Teacher teacher);

    public Teacher get(Long id);

    public List<Subject> getSubjects(Long teacherId);

}
