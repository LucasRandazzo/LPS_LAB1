
package com.lps.back.services.interfaces;

import java.util.List;

import com.lps.back.dtos.course.PostAndEditCourseRequest;
import com.lps.back.models.Curriculum;

public interface ICurriculumService {

    Curriculum get(Long id);

    // criar
    Curriculum create(PostAndEditCourseRequest obj);

    // pegar todos
    List<Curriculum> getAllCourses();

    // deletar
    Void deleteCourse(Long id);

}