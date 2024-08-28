package com.lps.back.services.interfaces;

import java.util.List;

import com.lps.back.dtos.registration.RegistrationSaveDTO;
import com.lps.back.dtos.registration.RegistrationsDeleteDTO;
import com.lps.back.models.Registration;

public interface IRegistrationService {

    void save(RegistrationSaveDTO registrationDTO);

    void removeSubjects(RegistrationsDeleteDTO registrationsDeleteDTO);

    Registration get(Long id);

    double getRegistrationValue(Long id);

    double getRegistrationValueByStudentId(Long studentId);

    List<Registration> getAll();

    List<Registration> getAllByStudentId(Long studentId);
}
