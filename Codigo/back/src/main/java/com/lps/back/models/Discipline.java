package com.lps.back.models;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "name", nullable = false, unique = false, columnDefinition = "VARCHAR(50)")
    protected String name;

    @Column(name = "credits", nullable = false, unique = true, columnDefinition = "int")
    protected int credits;

    @Column(name = "price", nullable = false, columnDefinition = "DOUBLE")
    protected Double price;

    @OneToMany(mappedBy = "discipline", fetch = FetchType.EAGER)
    protected List<Subject> subjects;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "gradle", joinColumns = @JoinColumn(name = "discipline_id"), inverseJoinColumns = @JoinColumn(name = "curse_id"))
    protected List<Course> courses;
}
