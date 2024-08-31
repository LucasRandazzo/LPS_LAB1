package com.lps.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lps.back.dtos.message.MessageDTO;
import com.lps.back.dtos.user.UserLoginDTO;
import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.dtos.user.UserTokenDto;
import com.lps.back.models.Usuario;
import com.lps.back.services.UserService;
import com.lps.back.services.interfaces.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<?> login(UserLoginDTO userLoginDTO)
    {
        long result = userService.login(userLoginDTO);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<?> register(UserRegisterDTO userRegisterDTO)
    {
        Usuario result = userService.register(userRegisterDTO);
        return ResponseEntity.ok(result.getId());
    }

    public ResponseEntity<?> startRecoverPasswordProcess(String email)
    {
        userService.startRecoverPasswordProcess(email);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> checkRecoverPasswordToken(UserTokenDto userTokenDto)
    {
        boolean result = userService.checkRecoverPasswordToken(userTokenDto);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<?> changePassword(UserLoginDTO userLoginDTO)
    {
        userService.changePassword(userLoginDTO);
        return ResponseEntity.noContent().build();
    }

}
