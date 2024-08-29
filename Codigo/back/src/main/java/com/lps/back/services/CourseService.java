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
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course get(Long id) {
        Course course = courseRepository.findById(id).get();
        return course;
    }

    @Override
    public Course create(PostAndEditCourseRequest newCourse) {
        Course obj = new Course();
        obj.setCredits(newCourse.credits());
        obj.setName(newCourse.name());

        Course createdCourse = courseRepository.save(obj);
        return createdCourse;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Void deleteCourse(Long id) {
        courseRepository.deleteById(id);
        return null;
    }

    @Override
    public Course editCourse(Long id, PostAndEditCourseRequest newCourse) {
        Course existingCourse = this.courseRepository.findById(id).get();
        existingCourse.setCredits(newCourse.credits());
        existingCourse.setName(newCourse.name());

        Course savedCourse = this.courseRepository.save(existingCourse);

        return savedCourse;
    }
}
