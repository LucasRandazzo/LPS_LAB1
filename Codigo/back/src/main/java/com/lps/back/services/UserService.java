package com.lps.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Security;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.lps.back.config.SecurityConfig;
import com.lps.back.dtos.user.UserLoginDTO;
import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.dtos.user.UserTokenDto;
import com.lps.back.mappers.UsuarioMapper;
import com.lps.back.models.Usuario;
import com.lps.back.repositories.UsuarioRepository;
import com.lps.back.services.interfaces.IUserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService implements IUserService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public long login(UserLoginDTO userLoginDTO) {
        Usuario usuario = usuarioRepository.findByEmail(userLoginDTO.email());

        if (usuario == null) {
            throw new EntityNotFoundException("Usuário não cadastrado");
        }

        String encodePassword = SecurityConfig.passwordEncoder().encode(userLoginDTO.password());

        if (!encodePassword.equals(usuario.getPassword())) {
            throw new IllegalArgumentException("Senha incorreta");
        }

        return usuario.getId();
    }

    @Override
    public Usuario register(UserRegisterDTO userRegisterDTO) {
        Usuario user = usuarioRepository.findByEmail(userRegisterDTO.email());

        if (user != null) {
            throw new IllegalArgumentException("Usuário já cadastrado");
        }

        user = UsuarioMapper.dtoToModel(userRegisterDTO);

        return user;
    }

    @Override
    public void startRecoverPasswordProcess(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startRecoverPasswordProcess'");
    }

    @Override
    public boolean checkRecoverPasswordToken(UserTokenDto userTokenDto) {
        String token = createToken(userTokenDto.email());

        if (token.equals(userTokenDto.token())) {
            return true;
        }

        return false;
    }

    @Override
    public void changePassword(UserLoginDTO userLoginDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
    }

    private String createToken(String texto) {
        var BIG_PRIME_NUMBER = 97;
        String newToken = "";

        for (int i = 0; i < 4; i++) {
            var pos = (BIG_PRIME_NUMBER * i) % texto.length();
            newToken += texto.charAt(pos);
        }

        newToken = newToken.toUpperCase();

        return newToken;
    }

}
