package com.lps.back.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "student_id", nullable = false, unique = false)
    protected Student student;

    @ManyToOne()
    @JoinColumn(name = "course_id", nullable = false, unique = false)
    protected Course course;

    @ManyToMany(mappedBy = "registrations", fetch = FetchType.EAGER)
    protected List<Subject> subjects;

    public Long getStudentId() {
        return student.getId();
    }

    public void removeSubjects(List<Subject> subjects) {
        this.subjects.removeAll(subjects);
    }
}
