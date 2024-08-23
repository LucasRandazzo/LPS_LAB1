package com.lps.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lps.back.dtos.RegistrationDTO;
import com.lps.back.models.Student;
import com.lps.back.services.RegistrationService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController()
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody RegistrationDTO registrationDTO) {
        try {
            registrationService.save(registrationDTO);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

        return ResponseEntity.status(201).body("Registration saved successfully");
    }

    // @DeleteMapping("/{id}/delete")
    // public ResponseEntity<?> getAllStudentSubject(@PathVariable Long id) {
    // registrationService.removeSubjects(id);

    // return ResponseEntity.status(200).body("Registration deleted successfully");

    // }
}
