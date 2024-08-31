package com.lps.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.back.models.Curriculum;



@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

}