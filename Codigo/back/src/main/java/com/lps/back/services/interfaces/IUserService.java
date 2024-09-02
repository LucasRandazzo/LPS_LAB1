package com.lps.back.services.interfaces;

import com.lps.back.dtos.user.UserLoginDTO;
import com.lps.back.dtos.user.UserRecoverPasswordDTO;
import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.dtos.user.UserReturnLoginDTO;
import com.lps.back.dtos.user.UserTokenDto;
import com.lps.back.dtos.user.UserUpdateDTO;
import com.lps.back.models.Usuario;

public interface IUserService {

    UserReturnLoginDTO login(UserLoginDTO userLoginDTO);

    Usuario register(UserRegisterDTO userRegisterDTO);

    void startRecoverPasswordProcess(String email);

    boolean checkRecoverPasswordToken(UserTokenDto userTokenDto);

    void changePassword(UserRecoverPasswordDTO userRecoverPasswordDTO);

    void edit(UserUpdateDTO userRegisterDTO);
}
