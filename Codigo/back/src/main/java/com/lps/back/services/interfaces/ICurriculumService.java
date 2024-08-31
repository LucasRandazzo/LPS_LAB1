
package com.lps.back.services.interfaces;

import java.util.List;

import com.lps.back.dtos.curriculum.createCurriculumRequest;
import com.lps.back.models.Curriculum;

public interface ICurriculumService {

    Curriculum get(Long id);

    // criar
    Curriculum create(createCurriculumRequest obj);

    // pegar todos
    List<Curriculum> getAllCurriculums();

    // deletar
    Void deleteCurriculum(Long id);

}