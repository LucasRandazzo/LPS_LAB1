package com.lps.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.back.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}