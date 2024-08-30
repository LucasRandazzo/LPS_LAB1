package com.lps.back.services.interfaces;

import java.util.List;

import com.lps.back.models.Course;
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.utils.SubjectSituationEnum;

public interface ISubjectService {
    Subject get(Long id); // method to get one Subject by id

    List<Subject> getList(List<Long> ids); // method to get all Subjects

    void checkSubjectIsAvailable(Subject subject, Long studentId); // method to check if a Subject is available

    void checkSubjectCurseIsValid(Subject subject, Course course); // method to check if a Subject is valid

    void checkSubjectsSituation(List<Subject> subjects, Course course, Long studentId);

    List<Student> getStudents(Long subjectID);

    List<Subject> getByCurseIdAndSituation(Long id, SubjectSituationEnum situationEnum);

    void save(Subject subject);
}
