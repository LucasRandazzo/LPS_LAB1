package com.lps.back.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lps.back.models.Subject;
import com.lps.back.models.Teacher;
import com.lps.back.repositories.TeacherRepository;
import com.lps.back.services.interfaces.ITeacherService;

public class TeacherService implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
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

}