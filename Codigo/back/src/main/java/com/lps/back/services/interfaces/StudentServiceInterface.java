package com.lps.back.services.interfaces;

import com.lps.back.models.Student;
import java.util.List;

public interface StudentServiceInterface {

    public void addRegistration(Long studentId, List<Long> subjectsIds, Long CourseId);

    public void removeRegistration(Long studentId, Long subjectId);

    public void addStudent(Long id, String name, String mail, String password);

    public void removeStudent(Long id);

    public Student get(Long id);

}
