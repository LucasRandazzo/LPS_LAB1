package com.lps.back.services.interfaces;

import java.util.List;

import com.lps.back.dtos.subject.SubjectDTO;
import com.lps.back.dtos.subject.SubjectRegisterDTO;
import com.lps.back.models.Curriculum;
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.models.Teacher;
import com.lps.back.utils.SubjectSituationEnum;

public interface ISubjectService {
    Subject get(Long id); // method to get one Subject by id

    List<Subject> getList(List<Long> ids); // method to get all Subjects

    List<SubjectDTO> getAll(); // method to get all Subjects

    void checkSubjectsSituation(List<Subject> subjects, Curriculum course, Long studentId);

    List<Student> getStudents(Long subjectID);

    List<Subject> getListByTeacherId(Long id); // method to get all Subjects

    List<Subject> getByCurseIdAndSituation(Long id, SubjectSituationEnum situationEnum);

    void save(SubjectRegisterDTO subject);

    SubjectSituationEnum changeStatus(Long id, SubjectSituationEnum situationEnum);

    SubjectSituationEnum checkAndUpdateSituation(Subject subject);

    void removeTeacher(Subject subject, Teacher teacher);

}
