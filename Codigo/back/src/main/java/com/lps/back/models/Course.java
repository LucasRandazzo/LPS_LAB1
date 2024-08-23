package com.lps.back.models;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name", nullable = false, unique = false, columnDefinition = "VARCHAR(50)")
    protected String name;

    @Column(name = "credits", nullable = false, unique = true, columnDefinition = "int")
    protected int credits;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "registration_id", nullable = false, unique = false)
    protected List<Registration> registrations;

    @ManyToMany(mappedBy = "courses")
    protected List<Discipline> disciplines; 
}
