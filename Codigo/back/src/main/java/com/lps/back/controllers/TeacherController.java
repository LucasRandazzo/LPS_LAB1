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

import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.models.Teacher;
import com.lps.back.services.interfaces.ITeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @PostMapping()
    public ResponseEntity<Teacher> createTeacher(@RequestBody UserRegisterDTO teacher) {
        return ResponseEntity.ok().body(this.teacherService.create(teacher));
    }

    @GetMapping()
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok().body(this.teacherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.teacherService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        this.teacherService.delete(id);
        return ResponseEntity.ok().build();
    }
}
