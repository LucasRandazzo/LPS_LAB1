package com.lps.back.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.back.dtos.registration.RegistrationSaveDTO;
import com.lps.back.dtos.registration.RegistrationsDeleteDTO;
import com.lps.back.models.Curriculum;
import com.lps.back.models.Registration;
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.repositories.RegistrationRepository;
import com.lps.back.services.interfaces.ICurriculumService;
import com.lps.back.services.interfaces.IRegistrationService;
import com.lps.back.services.interfaces.IStudentService;
import com.lps.back.services.interfaces.ISubjectService;
import com.lps.back.utils.SubjectSituationEnum;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegistrationService implements IRegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private ICurriculumService courseService;

    @Autowired
    private IStudentService studentService;

    // US - 09
    @Override
    public void save(RegistrationSaveDTO registrationDTO) {

        if (registrationDTO.subjectsIds().size() < 4 || registrationDTO.subjectsIds().size() > 6) {
            throw new IllegalArgumentException("The student must be enrolled in at least 4 and at most 6 subjects");
        }

        Student student = studentService.get(registrationDTO.studentId());
        List<Subject> subjects = subjectService.getList(registrationDTO.subjectsIds());
        Curriculum course = courseService.get(registrationDTO.courseId());
        subjectService.checkSubjectsSituation(subjects, course, registrationDTO.studentId());
        Registration registration = new Registration(null, student, course, subjects);
        registrationRepository.save(registration);

    }

    @Override
    public Registration get(Long id) {

        Registration registration = registrationRepository.findById(id).get();

        return registration;
    }

    // US - 10
    @Override
    public void removeSubjects(RegistrationsDeleteDTO registrationsDeleteDTO) {
        Registration registration = this.get(registrationsDeleteDTO.registrationId());
        List<Subject> subjects = subjectService.getList(registrationsDeleteDTO.subjectsIds());
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
            throw new EntityNotFoundException("Registration not found, based in student id");

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

    @Override
    public List<Registration> getAll() {
        List<Registration> registrations = registrationRepository.findAll();

        return registrations;
    }

    @Override
    public List<Registration> getAllByStudentId(Long studentId) {
        List<Registration> registrations = registrationRepository.findByStudent_Id(studentId);
        if (registrations == null || registrations.isEmpty()) {
            throw new EntityNotFoundException("Registration not found, based in student id");

        }
        return registrations;
    }

}
