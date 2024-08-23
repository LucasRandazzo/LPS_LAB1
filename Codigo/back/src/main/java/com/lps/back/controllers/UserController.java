package com.lps.back.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.lps.back.dtos.user.UserLoginDTO;
import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.dtos.user.UserTokenDto;
import com.lps.back.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public ResponseEntity<?> login(UserLoginDTO userLoginDTO)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    public ResponseEntity<?> register(UserRegisterDTO userRegisterDTO)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    public ResponseEntity<?> startRecoverPasswordProcess(String email)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    public ResponseEntity<?> checkRecoverPasswordToken(UserTokenDto userTokenDto)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    public ResponseEntity<?> changePassword(UserLoginDTO userLoginDTO)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

}
