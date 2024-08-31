package com.lps.back.services.interfaces;

import java.util.List;

import com.lps.back.models.Curriculum;
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.utils.SubjectSituationEnum;

public interface ISubjectService {
    Subject get(Long id); // method to get one Subject by id

    List<Subject> getList(List<Long> ids); // method to get all Subjects

    void checkSubjectsSituation(List<Subject> subjects, Curriculum course, Long studentId);

    List<Student> getStudents(Long subjectID);

    List<Subject> getListByTeacherId(Long id); // method to get all Subjects

    List<Subject> getByCurseIdAndSituation(Long id, SubjectSituationEnum situationEnum);

    void save(Subject subject);

    SubjectSituationEnum changeStatus(Long id, SubjectSituationEnum situationEnum);

    SubjectSituationEnum checkAndUpdateSituation(Subject subject);

}
