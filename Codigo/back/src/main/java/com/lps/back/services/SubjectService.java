package com.lps.back.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.back.dtos.subject.SubjectDTO;
import com.lps.back.dtos.subject.SubjectRegisterDTO;
import com.lps.back.mappers.SubjectMapper;
import com.lps.back.models.Curriculum;
import com.lps.back.models.Discipline;
import com.lps.back.models.Registration;
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.models.Teacher;
import com.lps.back.repositories.SubjectRepository;
import com.lps.back.services.interfaces.IDisciplineService;
import com.lps.back.services.interfaces.ISubjectService;
import com.lps.back.services.interfaces.ITeacherService;
import com.lps.back.utils.SubjectSituationEnum;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SubjectService implements ISubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IDisciplineService disciplineService;

    @Override
    public Subject get(Long id) {
        Subject subject = subjectRepository.findById(id).get();

        return subject;
    }

    @Override
    public List<Subject> getList(List<Long> ids) {
        List<Subject> subjects = subjectRepository.findAllById(ids);
        if (subjects == null || subjects.isEmpty() || subjects.size() != ids.size()) {
            throw new EntityNotFoundException("Subjects not found");
        }
        return subjects;
    }

    private void checkSubjectIsAvailable(Subject subject, Long studentId) {
        switch (subject.getSituation()) {
            case Closed:
                throw new IllegalArgumentException(
                        "The subject " + subject.getName()
                                + " is closed, because the number of student, can't be more than 60");
            case Canceled:
                throw new IllegalArgumentException(
                        "The subject " + subject.getName()
                                + " is canceled, because the number of student, can't be less than 3");
            case Available:
                subject.getRegistrations().forEach(r -> {
                    if (r.getStudentId() == studentId) {
                        throw new IllegalArgumentException(
                                "The student is already enrolled in the subject " + subject.getName());
                    }
                });
                break;
            default:
                break;
        }
    }

    private void checkSubjectCurseIsValid(Subject subject, Curriculum curriculum) {
        if (!subject.getCurriculums().contains(curriculum)) {
            throw new IllegalArgumentException(
                    "The subject " + subject.getName() + " is not part of the curriculum "
                            + curriculum.getCourse().getName());
        }
    }

    @Override
    public void checkSubjectsSituation(List<Subject> subjects, Curriculum curriculum, Long studentId) {
        this.checkSituation(subjects);
        subjects.forEach(s -> {
            this.checkSubjectIsAvailable(s, studentId);
            this.checkSubjectCurseIsValid(s, curriculum);

        });
    }

    @Override
    public List<Student> getStudents(Long subjectID) {
        Subject subject = this.get(subjectID);
        List<Student> students = subject.getRegistrations().stream().map(r -> r.getStudent()).toList();
        if (students == null || students.isEmpty()) {
            throw new EntityNotFoundException("Students not found");
        }
        return students;
    }

    @Override
    public void save(SubjectRegisterDTO dto) {
        Discipline discipline = disciplineService.get(dto.disciplineId());
        List<Teacher> teachers = teacherService.getAllByIds(dto.teachersIds());
        Subject subject = new Subject(null, dto.price(), SubjectSituationEnum.Available, teachers, discipline,
                new ArrayList<Registration>());
                
        subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getByCurseIdAndSituation(Long id, SubjectSituationEnum situationEnum) {

        return subjectRepository.findByDisciplineCurriculumsIdAndSituation(id, situationEnum);
    }

    @Override
    public List<Subject> getListByTeacherId(Long id) {
        return subjectRepository.findByTeachersId(id);
    }

    private void checkSituation(List<Subject> subjects) {
        for (Subject subject : subjects) {
            if (checkAndUpdateSituation(subject) != SubjectSituationEnum.Available) {
                throw new IllegalArgumentException("The subject is not available");
            }

        }
    }

    @Override
    public SubjectSituationEnum checkAndUpdateSituation(Subject subject) {

        if (subject.getRegistrations().size() == 60) {
            subject.setSituation(SubjectSituationEnum.Closed);
            subjectRepository.save(subject);
        }

        return subject.getSituation();
    }

    @Override
    public SubjectSituationEnum changeStatus(Long id, SubjectSituationEnum situationEnum) {
        Subject subject = this.get(id);
        subject.setSituation(situationEnum);
        subjectRepository.save(subject);
        return situationEnum;
    }

    @Override
    public void removeTeacher(Subject subject, Teacher teacher) {
        subject.getTeachers().remove(teacher);
        subjectRepository.save(subject);
    }

    @Override
    public List<SubjectDTO> getAll() {
        List<Subject> subjects = subjectRepository.findAll();
        if (subjects == null || subjects.isEmpty()) {
            throw new EntityNotFoundException("Subjects not found");
        }
        return subjects.stream().map(subject -> SubjectMapper.toDto(subject))
                .collect(Collectors.toList());
    }
}
