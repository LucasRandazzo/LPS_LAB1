package com.lps.back.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.back.dtos.discipline.PostAndEditDisciplineRequest;
import com.lps.back.models.Curriculum;
import com.lps.back.models.Discipline;
import com.lps.back.repositories.DisciplineRepository;
import com.lps.back.services.interfaces.ICurriculumService;
import com.lps.back.services.interfaces.IDisciplineService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DisciplineService implements IDisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private ICurriculumService curriculumservice;

    @Override
    public Discipline get(Long id) {
        return this.disciplineRepository.findById(id).get();
    }

    @Override
    public Discipline create(PostAndEditDisciplineRequest obj) {
        Discipline createdObj = new Discipline();

        createdObj.setName(obj.name());
        createdObj.setCredits(obj.credits());

        ArrayList<Curriculum> curriculums = new ArrayList<>();
        for (Long id : obj.curriculumsId()) {
            curriculums.add(this.curriculumservice.get(id));
        }
        createdObj.setCurriculums(curriculums);

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

        ArrayList<Curriculum> curriculums = new ArrayList<>();
        for (Long id_course : newDiscipline.curriculumsId()) {
            curriculums.add(this.curriculumservice.get(id_course));
        }
        existingDiscipline.setCurriculums(curriculums);

        return this.disciplineRepository.save(existingDiscipline);
    }

}
