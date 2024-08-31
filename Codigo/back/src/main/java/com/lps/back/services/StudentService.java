package com.lps.back.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.back.dtos.registration.RegistrationsDeleteDTO;
import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.models.Usuario;
import com.lps.back.repositories.StudentRepository;
import com.lps.back.services.interfaces.IRegistrationService;
import com.lps.back.services.interfaces.IStudentService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    IRegistrationService registrationService;

    @Override
    public Student create(UserRegisterDTO student) {
        Usuario user = userService.register(student);

        Student newStudent = new Student();
        newStudent.setId(user.getId());
        newStudent.setEmail(user.getEmail());
        newStudent.setName(user.getName());
        newStudent.setPassword(user.getPassword());

        return this.studentRepository.save(newStudent);
    }

    @Override
    public Student get(Long id) {
        Student student = studentRepository.findById(id).get();
        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = studentRepository.findAll();
        if (students == null || students.isEmpty()) {
            throw new EntityNotFoundException("Students not found");
        }
        return students;
    }

    @Override
    public void delete(Long id) {
        Student student = studentRepository.findById(id).get();
        student.getRegistrations().forEach(registration -> {
            List<Long> subjects = registration.getSubjects().stream().map(Subject::getId).collect(Collectors.toList());
            RegistrationsDeleteDTO registrationsDeleteDTO = new RegistrationsDeleteDTO(registration.getId(),
                    subjects);

            registrationService.removeSubjects(registrationsDeleteDTO);
        });

        studentRepository.delete(student);
    }

}
