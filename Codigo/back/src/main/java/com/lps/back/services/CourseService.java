package com.lps.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (course == null) {
            throw new RuntimeException("Course not found");
        }
        return course;
    }

}
