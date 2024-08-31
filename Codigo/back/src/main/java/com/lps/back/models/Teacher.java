package com.lps.back.models;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Teacher extends Usuario {

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected List<Subject> subjects;

    public Teacher(Long id, String name, String mail, String password, List<Subject> subjects) {
        super(id, name, mail, password);
        subjects = this.subjects;
    }

    public Teacher(String name, String mail, String password) {
        super(name, mail, password);
    }

}
