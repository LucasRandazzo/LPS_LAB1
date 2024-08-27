package com.lps.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lps.back.dtos.message.MessageDTO;
import com.lps.back.dtos.registration.RegistrationSaveDTO;
import com.lps.back.dtos.registration.RegistrationsDeleteDTO;
import com.lps.back.services.RegistrationService;

@RestController()
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping()
    public ResponseEntity<MessageDTO> save(@RequestBody RegistrationSaveDTO registrationDTO) {

        registrationService.save(registrationDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageDTO("Registration created successfully", "success"));

    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getByStudentId(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(registrationService.getAllByStudentId(id));

    }

    @GetMapping()
    public ResponseEntity<?> getAll() {

        return ResponseEntity.status(HttpStatus.OK).body(registrationService.getAll());

    }

    @DeleteMapping()
    public ResponseEntity<MessageDTO> deleteSubjects(@RequestBody RegistrationsDeleteDTO registrationDTO) {

        registrationService.removeSubjects(registrationDTO);
        MessageDTO messageDTO = new MessageDTO("Subjects removed successfully", "success");

        return ResponseEntity.status(HttpStatus.OK).body(messageDTO);

    }
}
