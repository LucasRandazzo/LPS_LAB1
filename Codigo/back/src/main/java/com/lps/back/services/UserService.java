package com.lps.back.services;

import org.springframework.stereotype.Service;

import com.lps.back.dtos.user.UserLoginDTO;
import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.dtos.user.UserTokenDto;
import com.lps.back.services.interfaces.IUserService;

@Service
public class UserService implements IUserService {

    @Override
    public long login(UserLoginDTO userLoginDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public long register(UserRegisterDTO userRegisterDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public void startRecoverPasswordProcess(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startRecoverPasswordProcess'");
    }

    @Override
    public boolean checkRecoverPasswordToken(UserTokenDto userTokenDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkRecoverPasswordToken'");
    }

    @Override
    public void changePassword(UserLoginDTO userLoginDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
    }

}
