package com.lps.back.services;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.poi.hpsf.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.back.models.Course;
import com.lps.back.models.Registration;
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.repositories.RegistrationRepository;
import com.lps.back.services.interfaces.CourseServiceInterface;
import com.lps.back.services.interfaces.RegistrationServiceInterface;
import com.lps.back.services.interfaces.StudentServiceInterface;
import com.lps.back.services.interfaces.SubjectServiceInterface;
import com.lps.back.utils.SubjectSituationEnum;

import jakarta.transaction.Transactional;

import java.util.*;

@Service
@Transactional
public class RegistrationService implements RegistrationServiceInterface {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private SubjectServiceInterface subjectService;

    @Autowired
    private CourseServiceInterface courseService;

    @Autowired
    private StudentServiceInterface studentService;

    // US - 09
    @Override
    public void save(Long studentId, List<Long> subjectsIds, Long courseId) {

        if (subjectsIds.size() < 4 || subjectsIds.size() > 6) {
            throw new IllegalArgumentException("The student must be enrolled in at least 4 and at most 6 subjects");
        }

        Student student = studentService.get(studentId);
        List<Subject> subjects = subjectService.getList(subjectsIds);
        Course course = courseService.get(courseId);
        subjectService.checkSubjectsSituation(subjects, course, studentId);

        Registration registration = new Registration(null, student, course, null);
        registrationRepository.save(registration);
        registration.setSubjects(subjects);
        registrationRepository.save(registration);

    }

    @Override
    public Registration get(Long id) {

        Registration registration = registrationRepository.findById(id).get();
        if (registration == null) {
            throw new IllegalArgumentException("Registration not found");
        }
        return registration;
    }

    // US - 10
    @Override
    public void removeSubjects(Long registrationId, List<Long> subjectsIds) {
        Registration registration = this.get(registrationId);
        List<Subject> subjects = subjectService.getList(subjectsIds);
        registration.removeSubjects(subjects);

        if (registration.getSubjects().isEmpty()) {
            registrationRepository.delete(registration);
        } else {

            if (registration.getSubjects().size() < 4) {
                throw new IllegalArgumentException("The student must be enrolled in at least 4 subjects");
            }
            registrationRepository.save(registration);
        }

    }

    // TODO: the following methods probably should be in billing system, please
    // check the teacher opinion
    @Override
    public double getRegistrationValue(Long id) {
        Registration registration = this.get(id);
        double registrationValue = 0;

        for (Subject subject : registration.getSubjects()) {
            if (subject.getSituation() == SubjectSituationEnum.InProgress) {
                registrationValue += subject.getPrice();
            }
        }

        return registrationValue;
    }

    @Override
    public double getRegistrationValueByStudentId(Long studentId) {
        double registrationValue = 0;
        List<Registration> registrations = registrationRepository.findByStudent_Id(studentId);
        if (registrations == null || registrations.isEmpty()) {
            throw new IllegalArgumentException("Registration not found, based in student id");

        }
        for (Registration registration : registrations) {
            for (Subject subject : registration.getSubjects()) {
                if (subject.getSituation() == SubjectSituationEnum.InProgress) {
                    registrationValue += subject.getPrice();
                }
            }
        }
        return registrationValue;
    }

}
