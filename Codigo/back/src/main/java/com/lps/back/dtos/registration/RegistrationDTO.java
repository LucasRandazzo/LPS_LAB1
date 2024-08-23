package com.lps.back.dtos.registration;

import java.util.List;
/**
 * RegistrationDTO
 */
public record RegistrationDTO(Long studentId, List<Long> subjectsIds, Long courseId) {
}