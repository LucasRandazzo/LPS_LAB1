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

import com.lps.back.dtos.curriculum.createCurriculumRequest;
import com.lps.back.models.Curriculum;
import com.lps.back.services.interfaces.ICurriculumService;

@RestController()
@RequestMapping("/curriculum")
public class CurriculumController {

    @Autowired
    private ICurriculumService curriculumService;

    @GetMapping()
    public ResponseEntity<List<Curriculum>> getAllCurriculums() {
        List<Curriculum> Curriculums = this.curriculumService.getAllCurriculums();
        return ResponseEntity.ok().body(Curriculums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curriculum> getCurriculum(@PathVariable Long id) {
        Curriculum Curriculum = this.curriculumService.get(id);
        return ResponseEntity.ok().body(Curriculum);
    }

    @PostMapping("")
    public ResponseEntity<Curriculum> postCurriculum(@RequestBody createCurriculumRequest newCurriculum) {
        Curriculum createdCurriculum = this.curriculumService.create(newCurriculum);
        return ResponseEntity.ok().body(createdCurriculum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurriculum(@PathVariable Long id) {
        this.curriculumService.deleteCurriculum(id);
        return ResponseEntity.ok().build();
    }

}
