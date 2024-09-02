package com.lps.back.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.models.Subject;
import com.lps.back.models.Teacher;
import com.lps.back.models.Usuario;
import com.lps.back.repositories.TeacherRepository;
import com.lps.back.services.interfaces.ISubjectService;
import com.lps.back.services.interfaces.ITeacherService;
import com.lps.back.services.interfaces.IUserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TeacherService implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private IUserService userService;

    @Override
    public Teacher create(UserRegisterDTO teacher) {
        Usuario user = userService.register(teacher);

        Teacher newTeacher = new Teacher();
        newTeacher.setId(user.getId());
        newTeacher.setEmail(user.getEmail());
        newTeacher.setName(user.getName());
        newTeacher.setPassword(user.getPassword());

        return this.teacherRepository.save(newTeacher);
    }

    @Override
    public Teacher get(Long id) {
        Teacher teacher = teacherRepository.findById(id).get();
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher not found");
        }
        return teacher;
    }

    @Override
    public List<Subject> getSubjects(Long teacherId) {
        Teacher teacher = this.get(teacherId);
        List<Subject> subjects = teacher.getSubjects();
        if (subjects == null || subjects.isEmpty()) {
            throw new IllegalArgumentException("Subjects not found");
        }
        return subjects;
    }

    @Override
    public List<Teacher> getAll() {
        return this.teacherRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Teacher teacher = this.get(id);
        teacher.getSubjects().forEach(subject -> {
            subjectService.removeTeacher(subject, teacher);
        });
        this.teacherRepository.delete(teacher);
    }

    @Override
    public List<Teacher> getAllByIds(List<Long> ids) {
        return teacherRepository.findAllById(ids);
    }

}
