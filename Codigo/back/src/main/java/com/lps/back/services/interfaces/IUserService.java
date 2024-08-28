package com.lps.back.services.interfaces;

import com.lps.back.dtos.user.UserLoginDTO;
import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.dtos.user.UserTokenDto;

public interface IUserService {

    long login(UserLoginDTO userLoginDTO);

    long register(UserRegisterDTO userRegisterDTO);

    void startRecoverPasswordProcess(String email);

    boolean checkRecoverPasswordToken(UserTokenDto userTokenDto);

    void changePassword(UserLoginDTO userLoginDTO);

}
