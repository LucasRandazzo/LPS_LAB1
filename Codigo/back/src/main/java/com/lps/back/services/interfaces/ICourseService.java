
package com.lps.back.services.interfaces;

import java.util.List;

import com.lps.back.dtos.course.PostAndEditCourseRequest;
import com.lps.back.models.Course;

public interface ICourseService {

    Course get(Long id);
    // criar
    Course create(PostAndEditCourseRequest obj);
    // pegar todos
    List<Course> getAllCourses();
    // deletar
    Void deleteCourse(Long id);
    // editar
    Course editCourse(Long id, PostAndEditCourseRequest newCourse);
}