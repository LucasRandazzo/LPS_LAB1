package com.lps.back.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curse_id", nullable = false, unique = false)
    protected Course course;

    @OneToMany(fetch = FetchType.EAGER)
    protected List<Registration> registrations;

    @ManyToMany(mappedBy = "curriculums", fetch = FetchType.EAGER)
    protected List<Discipline> disciplines;

    public int getCredits() {
        return this.disciplines.stream().mapToInt(Discipline::getCredits).sum();
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getName() {
        return course.getName();
    }
}
