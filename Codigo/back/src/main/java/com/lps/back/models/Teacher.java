package com.lps.back.models;

import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Teacher extends Usuario {

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.EAGER)
    protected List<Subject> subjects;

    public Teacher(Long id, String name, String mail, String password, List<Subject> subjects) {
        super(id, name, mail, password);
        subjects = this.subjects;
    }

}
