package com.lps.back.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.PrimaryKeyJoinColumn;

@PrimaryKeyJoinColumn(name = "id")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Usuario {

    @OneToMany(mappedBy = "student")
    List<Registration> registrations;

}
