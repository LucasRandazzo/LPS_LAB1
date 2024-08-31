package com.lps.back.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.back.dtos.curriculum.createCurriculumRequest;
import com.lps.back.models.Course;
import com.lps.back.models.Curriculum;
import com.lps.back.models.Discipline;
import com.lps.back.models.Registration;
import com.lps.back.repositories.CurriculumRepository;
import com.lps.back.services.interfaces.ICourseService;
import com.lps.back.services.interfaces.ICurriculumService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CurriculumService implements ICurriculumService {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private ICourseService courseService;

    @Override
    public Curriculum get(Long id) {
        return curriculumRepository.findById(id).get();
    }

    @Override
    public Curriculum create(createCurriculumRequest obj) {
        Course course = courseService.get(obj.courseId());
        Curriculum curriculum = new Curriculum(null, course, new ArrayList<Registration>(),
                new ArrayList<Discipline>());
        return curriculumRepository.save(curriculum);
    }

    @Override
    public List<Curriculum> getAllCurriculums() {
        return curriculumRepository.findAll();
    }

    @Override
    public Void deleteCurriculum(Long id) {
        curriculumRepository.deleteById(id);
        return null;
    }

}
