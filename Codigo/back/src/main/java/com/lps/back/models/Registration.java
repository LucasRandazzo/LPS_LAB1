package com.lps.back.models;

import java.util.List;

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
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false, unique = false)
    protected Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false, unique = false)
    protected Curriculum course;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "subject_registrations", joinColumns = @JoinColumn(name = "registration_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    protected List<Subject> subjects;

    public Long getStudentId() {
        return student.getId();
    }

    public void removeSubjects(List<Subject> subjects) {
        this.subjects.removeAll(subjects);
    }
}
