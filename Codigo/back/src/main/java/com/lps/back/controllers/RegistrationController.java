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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lps.back.dtos.message.MessageDTO;
import com.lps.back.dtos.registration.RegistrationSaveDTO;
import com.lps.back.dtos.registration.RegistrationsDeleteDTO;
import com.lps.back.services.RegistrationService;

import jakarta.websocket.server.PathParam;

@RestController()
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping()
    public ResponseEntity<MessageDTO> save(@RequestBody RegistrationSaveDTO registrationDTO) {
        try {
            registrationService.save(registrationDTO);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new MessageDTO("Registration created successfully", "success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageDTO(e.getMessage(), "error"));
        }

    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getByStudentId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(registrationService.getAllByStudentId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageDTO(e.getMessage(), "error"));
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(registrationService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageDTO(e.getMessage(), "error"));
        }
    }

    @DeleteMapping()
    public ResponseEntity<MessageDTO> deleteSubjects(@RequestBody RegistrationsDeleteDTO registrationDTO) {
        try {
            registrationService.removeSubjects(registrationDTO);
            MessageDTO messageDTO = new MessageDTO("Subjects removed successfully", "success");

            return ResponseEntity.status(HttpStatus.OK).body(messageDTO);
        } catch (Exception e) {
            MessageDTO messageDTO = new MessageDTO(e.getMessage(), "error");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageDTO);
        }

    }
}
