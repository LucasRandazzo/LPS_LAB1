package com.lps.back.services.interfaces;

import com.lps.back.models.Registration;
import java.util.List;

public interface RegistrationServiceInterface {

    public void save(Long studentId, List<Long> subjectsIds, Long CourseId);

    public void removeSubjects(Long registrationId, List<Long> subjectsIds);

    public Registration get(Long id);

    public double getRegistrationValue(Long id);

    public double getRegistrationValueByStudentId(Long studentId);

}
