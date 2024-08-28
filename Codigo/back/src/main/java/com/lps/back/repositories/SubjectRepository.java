package com.lps.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.back.models.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
