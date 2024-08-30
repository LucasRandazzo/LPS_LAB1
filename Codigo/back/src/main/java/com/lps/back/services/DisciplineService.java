package com.lps.back.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.back.dtos.discipline.PostAndEditDisciplineRequest;
import com.lps.back.models.Course;
import com.lps.back.models.Discipline;
import com.lps.back.repositories.DisciplineRepository;
import com.lps.back.services.interfaces.ICourseService;
import com.lps.back.services.interfaces.IDisciplineService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DisciplineService implements IDisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private ICourseService courseService;

    @Override
    public Discipline get(Long id) {
        return this.disciplineRepository.findById(id).get();
    }

    @Override
    public Discipline create(PostAndEditDisciplineRequest obj) {
        Discipline createdObj = new Discipline();

        createdObj.setName(obj.name());
        createdObj.setCredits(obj.credits());
        createdObj.setPrice(obj.price());

        ArrayList<Course> courses = new ArrayList<>();
        for(Long id : obj.coursesIds()){
            courses.add(this.courseService.get(id));
        }
        createdObj.setCourses(courses);
        
        return this.disciplineRepository.save(createdObj);
    }

    @Override
    public List<Discipline> getAllDisciplines() {
        return this.disciplineRepository.findAll();
    }

    @Override
    public Void deleteDiscipline(Long id) {
        this.disciplineRepository.deleteById(id);
        return null;
    }

    @Override
    public Discipline editDiscipline(Long id, PostAndEditDisciplineRequest newDiscipline) {
        Discipline existingDiscipline = this.get(id);

        existingDiscipline.setCredits(newDiscipline.credits());
        existingDiscipline.setName(newDiscipline.name());
        existingDiscipline.setPrice(newDiscipline.price());

        ArrayList<Course> courses = new ArrayList<>();
        for(Long id_course : newDiscipline.coursesIds()){
            courses.add(this.courseService.get(id_course));
        }
        existingDiscipline.setCourses(courses);

        return this.disciplineRepository.save(existingDiscipline);
    }
    
}
