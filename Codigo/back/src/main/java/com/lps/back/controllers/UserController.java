package com.lps.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lps.back.dtos.message.MessageDTO;
import com.lps.back.dtos.user.UserLoginDTO;
import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.dtos.user.UserTokenDto;
import com.lps.back.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public ResponseEntity<?> login(UserLoginDTO userLoginDTO)
    {
        try {
            long result = userService.login(userLoginDTO);
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            var message = new MessageDTO("Error", "Ocorreu um erro inesperado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    public ResponseEntity<?> register(UserRegisterDTO userRegisterDTO)
    {
        try {
            long result = userService.register(userRegisterDTO);
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            var message = new MessageDTO("Error", "Ocorreu um erro inesperado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    public ResponseEntity<?> startRecoverPasswordProcess(String email)
    {
        try {
            userService.startRecoverPasswordProcess(email);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            var message = new MessageDTO("Error", "Ocorreu um erro inesperado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    public ResponseEntity<?> checkRecoverPasswordToken(UserTokenDto userTokenDto)
    {
        try {
            boolean result = userService.checkRecoverPasswordToken(userTokenDto);
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            var message = new MessageDTO("Error", "Ocorreu um erro inesperado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    public ResponseEntity<?> changePassword(UserLoginDTO userLoginDTO)
    {
        try {
            userService.changePassword(userLoginDTO);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            var message = new MessageDTO("Error", "Ocorreu um erro inesperado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

}
