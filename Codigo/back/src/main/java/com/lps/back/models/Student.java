package com.lps.back.models;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PrimaryKeyJoinColumn(name = "id")
@Entity
public class Student extends Usuario {

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected List<Registration> registrations;

    public Student(Long id, String name, String mail, String password, List<Registration> registrations) {
        super(id, name, mail, password);
    }

    public Student(String name, String mail, String password) {
        super(name, mail, password);
    }

}
