package com.lps.back.services;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public Usuario login(UserLoginDTO userLoginDTO) {
        Usuario usuario = usuarioRepository.findByEmail(userLoginDTO.email());

        if (usuario == null) {
            throw new EntityNotFoundException("Usuário não cadastrado");
        }

        boolean matchesPassword = SecurityConfig.passwordEncoder().matches(userLoginDTO.password(),
                usuario.getPassword());

        if (!matchesPassword) {
            throw new IllegalArgumentException("Senha incorreta");
        }

        return usuario;
    }

    @Override
    public Usuario register(UserRegisterDTO userRegisterDTO) {
        Usuario user = usuarioRepository.findByEmail(userRegisterDTO.email());

        if (user != null) {
            throw new IllegalArgumentException("Usuário já cadastrado");
        }

        user = UsuarioMapper.dtoToModel(userRegisterDTO);
        user.setId(null);
        usuarioRepository.save(user);

        return user;
    }

    @Override
    public void startRecoverPasswordProcess(String email) {
        Usuario user = usuarioRepository.findByEmail(email);

        if(user == null) {
            throw new EntityNotFoundException("Esse email não pertence a nenhum usuário registrado");
        }

        String token = createToken(email);

        try {
            emailSenderService.sendRecoveryPasswordMail(email, token);
        } catch (Exception e) {
            throw new RuntimeException("Email não enviado");
        }
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
        Usuario user = usuarioRepository.findByEmail(userLoginDTO.email());

        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        user.setPassword(userLoginDTO.password());
        usuarioRepository.save(user);
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
