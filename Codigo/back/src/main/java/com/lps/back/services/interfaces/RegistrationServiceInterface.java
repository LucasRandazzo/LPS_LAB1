package com.lps.back.services.interfaces;

import com.lps.back.models.Registration;
import java.util.List;

public interface RegistrationServiceInterface {
    public void addRegistration(Registration registration);

    public void removeRegistration(Long studentId, Long subjectId);

    public Registration get(Long id);

    public List<Registration> getAll();

    public List<Registration> getAllByCourseId(Long courseId);
}
