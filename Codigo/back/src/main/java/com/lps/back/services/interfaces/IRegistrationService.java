package com.lps.back.services.interfaces;

import com.lps.back.dtos.RegistrationDTO;
import com.lps.back.models.Registration;
import java.util.List;

public interface IRegistrationService {

    public void save(RegistrationDTO registrationDTO);

    void removeSubjects(Long registrationId, List<Long> subjectsIds);

    Registration get(Long id);

    double getRegistrationValue(Long id);

    double getRegistrationValueByStudentId(Long studentId);

}
