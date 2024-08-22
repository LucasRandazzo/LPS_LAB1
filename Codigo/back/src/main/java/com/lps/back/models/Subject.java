package com.lps.back.models;

import com.lps.back.utils.SubjectSituationEnum;
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
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price", nullable = false, columnDefinition = "DOUBLE")
    protected Double price;

    @Column(name = "situation", nullable = false, columnDefinition = "SMALLINT")
    protected SubjectSituationEnum situation;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "subject_teachers", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    protected List<Teacher> teachers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discipline_id", nullable = false, unique = false)
    Discipline discipline;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "subject_registrations", joinColumns = @JoinColumn(name = "registration_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    protected List<Registration> registrations;
}
