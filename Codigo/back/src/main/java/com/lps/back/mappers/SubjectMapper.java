package com.lps.back.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.lps.back.dtos.subject.SubjectDTO;
import com.lps.back.models.Subject;

public class SubjectMapper {
    public static SubjectDTO toDto(Subject subject) {
        List<String> curriculumsName = subject.getCurriculums().stream().map(curriculum -> curriculum.getName())
                .collect(Collectors.toList());
        return new SubjectDTO(subject.getId(), subject.getPrice(), subject.getSituation(),
                curriculumsName, subject.getName(), subject.getDiscipline().getCredits());
    }
}
