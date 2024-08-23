package com.lps.back.services;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;

import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.models.Teacher;
import com.lps.back.repositories.TeacherRepository;
import com.lps.back.services.interfaces.TeacherServiceInterface;

public class TeacherService implements TeacherServiceInterface {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void save(Teacher teacher) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
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
