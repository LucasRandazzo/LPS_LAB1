package com.lps.back.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.back.config.SecurityConfig;
import com.lps.back.dtos.user.UserLoginDTO;
import com.lps.back.dtos.user.UserRecoverPasswordDTO;
import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.dtos.user.UserReturnLoginDTO;
import com.lps.back.dtos.user.UserTokenDto;
import com.lps.back.mappers.UsuarioMapper;
import com.lps.back.models.Secretary;
import com.lps.back.models.Student;
import com.lps.back.models.Teacher;
import com.lps.back.models.Usuario;
import com.lps.back.repositories.UsuarioRepository;
import com.lps.back.services.interfaces.IUserService;
import com.lps.back.utils.UsuarioTypesEnum;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService implements IUserService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public UserReturnLoginDTO login(UserLoginDTO userLoginDTO) {
        Usuario usuario = usuarioRepository.findByEmail(userLoginDTO.email());

        if (usuario == null) {
            throw new EntityNotFoundException("Usuário não cadastrado");
        }

        boolean matchesPassword = SecurityConfig.passwordEncoder().matches(userLoginDTO.password(),
                usuario.getPassword());

        if (!matchesPassword) {
            throw new IllegalArgumentException("Senha incorreta");
        }

        UsuarioTypesEnum userType;
        if (usuario instanceof Teacher) {
            userType = UsuarioTypesEnum.TEACHER;
        } else if (usuario instanceof Student) {
            userType = UsuarioTypesEnum.STUDENT;
        } else if (usuario instanceof Secretary) {
            userType = UsuarioTypesEnum.SECRETARY;
        } else {
            throw new IllegalArgumentException("Tipo de usuário não reconhecido");
        }

        return UsuarioMapper.modelToDto(usuario, userType);
    }

    @Override
    public Usuario register(UserRegisterDTO userRegisterDTO) {
        Usuario user = usuarioRepository.findByEmail(userRegisterDTO.email());

        if (user != null) {
            throw new IllegalArgumentException("Usuário já cadastrado");
        }

        user = UsuarioMapper.dtoToModel(userRegisterDTO);
        String password = this.createToken(userRegisterDTO.email());
        String encryptedPassword = SecurityConfig.passwordEncoder().encode(password);
        user.setId(null);
        user.setPassword(encryptedPassword);
        usuarioRepository.save(user);
        try {
            emailSenderService.sendNewUser(user.getEmail(), password);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void startRecoverPasswordProcess(String email) {
        String decodedEmail;

        try {
            decodedEmail = URLDecoder.decode(email, StandardCharsets.UTF_8.name());
            decodedEmail = decodedEmail.replace("=", "");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Email não enviado");
        }

        Usuario user = usuarioRepository.findByEmail(decodedEmail);

        if (user == null) {
            throw new EntityNotFoundException("Esse email não pertence a nenhum usuário registrado");
        }

        String token = createToken(decodedEmail);

        try {
            emailSenderService.sendRecoveryPasswordMail(decodedEmail, token);
        } catch (Exception e) {
            throw new RuntimeException("Email não enviado");
        }
    }

    @Override
    public boolean checkRecoverPasswordToken(UserTokenDto userTokenDto) {
        boolean match = matchToken(userTokenDto.token(), userTokenDto.email());
        return match;
    }

    @Override
    public void changePassword(UserRecoverPasswordDTO userRecoverPasswordDTO) {
        Usuario user = usuarioRepository.findByEmail(userRecoverPasswordDTO.email());

        if (user == null) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        boolean match = matchToken(userRecoverPasswordDTO.token(), userRecoverPasswordDTO.email());

        if (!match) {
            throw new IllegalArgumentException("Token Inválido");
        }

        String newPassword = SecurityConfig.passwordEncoder().encode(userRecoverPasswordDTO.password());
        user.setPassword(newPassword);
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

    private boolean matchToken(String token, String email) {
        String tokenByEmail = createToken(email);
        return tokenByEmail.equals(token);
    }

}