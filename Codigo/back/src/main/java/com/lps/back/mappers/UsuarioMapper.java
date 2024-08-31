package com.lps.back.mappers;

import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.models.Secretary;
import com.lps.back.models.Student;
import com.lps.back.models.Teacher;
import com.lps.back.models.Usuario;

public class UsuarioMapper {
    public static Usuario dtoToModel(UserRegisterDTO userRegisterDTO) {
        Usuario usuario;

        switch (userRegisterDTO.userType()) {
            case STUDENT:
                usuario = new Student(userRegisterDTO.name(), userRegisterDTO.email(), userRegisterDTO.password())
                break;

            case TEACHER:
                usuario = new Teacher(userRegisterDTO.name(), userRegisterDTO.email(), userRegisterDTO.password());
                break;
            
            case SECRETARY:
                usuario = new Secretary(userRegisterDTO.name(), userRegisterDTO.email(), userRegisterDTO.password());  
                break;
    
            default:
                throw new IllegalArgumentException("Tipo do usuário não especificado");
                break;
        }

        return usuario;
    }
}
