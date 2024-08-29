package com.lps.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lps.back.dtos.discipline.PostAndEditDisciplineRequest;
import com.lps.back.models.Discipline;
import com.lps.back.services.interfaces.IDisciplineService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/discipline")
public class DisciplineController {
    
    @Autowired
    private IDisciplineService disciplineService;

    @GetMapping()
    public ResponseEntity<List<Discipline>> getAllDisciplines() {
        return ResponseEntity.ok().body(this.disciplineService.getAllDisciplines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discipline> getDisciplineByID(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.disciplineService.get(id));
    }

    @PostMapping()
    public ResponseEntity<Discipline> createDiscipline(@RequestBody PostAndEditDisciplineRequest request) {
        return ResponseEntity.ok().body(this.disciplineService.create(request));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Discipline> editDiscipline(@PathVariable Long id, @RequestBody PostAndEditDisciplineRequest request) {
        return ResponseEntity.ok().body(this.disciplineService.editDiscipline(id, request));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id){
        this.disciplineService.deleteDiscipline(id);

        return ResponseEntity.ok().build();
    }
}
