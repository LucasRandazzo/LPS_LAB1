package com.lps.back.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.back.models.Course;
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.repositories.SubjectRepository;
import com.lps.back.services.interfaces.ISubjectService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SubjectService implements ISubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

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

    @Override
    public void checkSubjectIsAvailable(Subject subject, Long studentId) {
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

    @Override
    public void checkSubjectCurseIsValid(Subject subject, Course course) {
        if (!subject.getCourses().contains(course)) {
            throw new IllegalArgumentException(
                    "The subject " + subject.getName() + " is not part of the course " + course.getName());
        }
    }

    @Override
    public void checkSubjectsSituation(List<Subject> subjects, Course course, Long studentId) {
        subjects.forEach(s -> {
            this.checkSubjectIsAvailable(s, studentId);
            this.checkSubjectCurseIsValid(s, course);

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
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

}