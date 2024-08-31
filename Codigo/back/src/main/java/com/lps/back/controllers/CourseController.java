package com.lps.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lps.back.dtos.course.PostAndEditCourseRequest;
import com.lps.back.models.Course;
import com.lps.back.services.interfaces.ICourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @GetMapping()
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = this.courseService.getAllCourses();
        return ResponseEntity.ok().body(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        Course course = this.courseService.get(id);
        return ResponseEntity.ok().body(course);
    }

    @PostMapping("")
    public ResponseEntity<Course> postCourse(@RequestBody PostAndEditCourseRequest newCourse) {
        Course createdCourse = this.courseService.create(newCourse);
        return ResponseEntity.ok().body(createdCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        this.courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }

}