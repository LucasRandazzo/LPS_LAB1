package com.lps.back.services.interfaces;

import com.lps.back.models.Registration;
import java.util.List;

public interface IRegistrationService {

    void save(Long studentId, List<Long> subjectsIds, Long CourseId);

    void removeSubjects(Long registrationId, List<Long> subjectsIds);

    Registration get(Long id);

    double getRegistrationValue(Long id);

    double getRegistrationValueByStudentId(Long studentId);

}
