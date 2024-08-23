package com.lps.back.services.interfaces;

import com.lps.back.dtos.registration.RegistrationSaveDTO;
import com.lps.back.dtos.registration.RegistrationsDeleteDTO;
import com.lps.back.models.Registration;
import java.util.List;

public interface IRegistrationService {

    public void save(RegistrationSaveDTO registrationDTO);

    void removeSubjects(RegistrationsDeleteDTO registrationsDeleteDTO);

    Registration get(Long id);

    double getRegistrationValue(Long id);

    double getRegistrationValueByStudentId(Long studentId);

}
