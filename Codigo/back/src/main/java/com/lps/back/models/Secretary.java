package com.lps.back.models;

import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

@PrimaryKeyJoinColumn(name = "id")
@Entity
@NoArgsConstructor
@Data
public class Secretary extends Usuario {

    public Secretary(Long id, String name, String mail, String password) {
        super(id, name, mail, password);
    }

    public Secretary(String name, String mail, String password){
        super(name, mail, password);
    }
}
