package com.lps.back.services.interfaces;

import com.lps.back.dtos.RegistrationDTO;
import com.lps.back.models.Registration;
import java.util.List;

public interface IRegistrationService {

    public void save(RegistrationDTO registrationDTO);

    public void removeSubjects(Long registrationId, List<Long> subjectsIds);

    public Registration get(Long id);

    public double getRegistrationValue(Long id);

    public double getRegistrationValueByStudentId(Long studentId);

}
