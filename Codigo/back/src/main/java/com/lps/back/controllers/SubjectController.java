package com.lps.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lps.back.services.SubjectService;
import com.lps.back.models.Student;
import java.util.List;

@RestController()
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/{id}/student")
    @ResponseBody
    public ResponseEntity<List<Student>> getAllStudentSubject(@PathVariable Long id) {

        return ResponseEntity.status(200).body(subjectService.getStudents(id));
    }
}
