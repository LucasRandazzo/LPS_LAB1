package com.lps.back.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.back.dtos.course.PostAndEditCourseRequest;
import com.lps.back.models.Course;
import com.lps.back.repositories.CourseRepository;
import com.lps.back.services.interfaces.ICourseService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CurseService implements ICourseService {

    @Autowired
    private CourseRepository CourseRepository;

    @Override
    public Course get(Long id) {
        Course course = CourseRepository.findById(id).get();
        return course;
    }

    @Override
    public Course create(PostAndEditCourseRequest newCourse) {
        Course obj = new Course();
        obj.setName(newCourse.name());

        Course createdCourse = CourseRepository.save(obj);
        return createdCourse;
    }

    @Override
    public List<Course> getAllCourses() {
        return CourseRepository.findAll();
    }

    @Override
    public Void deleteCourse(Long id) {
        CourseRepository.deleteById(id);
        return null;
    }

}
