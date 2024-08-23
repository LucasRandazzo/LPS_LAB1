package com.lps.back.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Entity
public class Student extends Usuario {

    @OneToMany(mappedBy = "student")
    protected List<Registration> registrations;

    Student(Long id, String name, String mail, String password, List<Registration> registrations) {
        super(id, name, mail, password);
    }

}
