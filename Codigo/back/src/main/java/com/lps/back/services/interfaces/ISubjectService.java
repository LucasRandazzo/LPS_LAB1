package com.lps.back.services.interfaces;

import com.lps.back.models.Course;
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import java.util.List;

public interface ISubjectService {
    Subject get(Long id); // method to get one Subject by id

    List<Subject> getList(List<Long> ids); // method to get all Subjects

    void checkSubjectIsAvailable(Subject subject, Long studentId); // method to check if a Subject is available

    void checkSubjectCurseIsValid(Subject subject, Course course); // method to check if a Subject is valid

    void checkSubjectsSituation(List<Subject> subjects, Course course, Long studentId);

    List<Student> getStudents(Long subjectID);

    void save(Subject subject);
}
