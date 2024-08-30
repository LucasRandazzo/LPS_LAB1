package com.lps.back.services.interfaces;

import java.util.List;

import com.lps.back.dtos.discipline.PostAndEditDisciplineRequest;
import com.lps.back.models.Discipline;

public interface IDisciplineService {
    Discipline get(Long id);
    // criar
    Discipline create(PostAndEditDisciplineRequest obj);
    // pegar todos
    List<Discipline> getAllDisciplines();
    // deletar
    Void deleteDiscipline(Long id);
    // editar
    Discipline editDiscipline(Long id, PostAndEditDisciplineRequest newDiscipline);
}
