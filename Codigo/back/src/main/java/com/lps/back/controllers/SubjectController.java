package com.lps.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.services.interfaces.ISubjectService;
import com.lps.back.utils.SubjectSituationEnum;

@RestController()
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @GetMapping("/{id}/student")
    @ResponseBody
    public ResponseEntity<List<Student>> getAllStudentSubject(@PathVariable Long id) {

        return ResponseEntity.status(200).body(subjectService.getStudents(id));
    }

    @GetMapping("/course/{id}/{situationEnum}")
    @ResponseBody
    public ResponseEntity<List<Subject>> getAllSubjectByCurseIdAndWaitingSituation(@PathVariable Long id,
            @PathVariable SubjectSituationEnum situationEnum) {

        return ResponseEntity.status(200).body(subjectService.getByCurseIdAndSituation(id, situationEnum));
    }
}
