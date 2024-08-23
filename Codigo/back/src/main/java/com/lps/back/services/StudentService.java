package com.lps.back.services;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;

import com.lps.back.models.Course;
import com.lps.back.models.Registration;
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.repositories.StudentRepository;
import com.lps.back.services.interfaces.CourseServiceInterface;
import com.lps.back.services.interfaces.RegistrationServiceInterface;
import com.lps.back.services.interfaces.StudentServiceInterface;
import com.lps.back.services.interfaces.SubjectServiceInterface;
import com.lps.back.utils.SubjectSituationEnum;

import io.jsonwebtoken.lang.Objects;

import java.util.List;

public class StudentService implements StudentServiceInterface {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectServiceInterface subjectService;

    @Autowired
    private CourseServiceInterface courseService;

    @Autowired
    private RegistrationServiceInterface registrationService;

    // TODO: implementar SubjectService e descomentar as linhas de codigo
    public void addRegistration(Long studentId, List<Long> subjectsIds, Long courseId) {

        if (subjectsIds.size() < 4 || subjectsIds.size() > 6) {
            throw new RuntimeException("The student must be enrolled in at least 4 and at most 6 subjects");
        }

        Student student = this.get(courseId);
        List<Subject> subject = subjectService.getList(subjectsIds);
        Course course = courseService.get(courseId);
        subject.forEach(s -> {
            subjectService.checkSubjectIsAvailable(s);
            // TODO: this method need to be in SubjectService and return a exception his
            // name need to be checkSubjectIsAvailable
            switch (s.getSituation()) {
                case Closed:
                    throw new RuntimeException(
                            "The subject " + s.getName()
                                    + " is closed, because the number of student, can't be more than 60");
                case Canceled:
                    throw new RuntimeException(
                            "The subject " + s.getName()
                                    + " is canceled, because the number of student, can't be less than 3");
                default:
                    break;
            }
            subjectService.checkSubjectCurseIsValid(s, course);
           
            // TODO: this method need to be in SubjectService and return a exception his
            // name need to be checkSubjectCurseIsValid
            if (student.getRegistrations().stream().anyMatch(r -> r.getSubjects().stream().anyMatch(sub -> sub.getDiscipline().equals(s.getDiscipline())))) {
                throw new RuntimeException("The student is already enrolled in a subject of this discipline");
            }
        });

        Registration registration = new Registration(null, student, course, subject);
        registrationService.addRegistration(registration);
        student.addRegistration(registration);

        studentRepository.save(student);

    }

    public void removeRegistration(Long studentId, Long subjectId) {
    }

    public void addStudent(Long id, String name, String mail, String password) {
    }

    public void removeStudent(Long id) {
    }

    @Override
    public Student get(Long id) {

        Student student = studentRepository.findById(id).get();
        if (student == null) {
            throw new RuntimeException("Student not found");
        }
        return student;
    }

}
