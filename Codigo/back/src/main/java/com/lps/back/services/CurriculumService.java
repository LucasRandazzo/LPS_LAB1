package com.lps.back.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lps.back.dtos.course.PostAndEditCourseRequest;
import com.lps.back.models.Curriculum;
import com.lps.back.services.interfaces.ICurriculumService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CurriculumService implements ICurriculumService {

    @Override
    public Curriculum get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public Curriculum create(PostAndEditCourseRequest obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public List<Curriculum> getAllCourses() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCourses'");
    }

    @Override
    public Void deleteCourse(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCourse'");
    }

}
