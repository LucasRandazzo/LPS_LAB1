package com.lps.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.back.models.Subject;
import com.lps.back.utils.SubjectSituationEnum;
import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByDisciplineCoursesIdAndSituation(Long id, SubjectSituationEnum situationEnum);
    List<Subject> findByTeachersId(Long id);
}
