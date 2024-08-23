package com.lps.back.dtos;

import java.util.List;
/**
 * RegistrationDTO
 */
public record RegistrationDTO(Long studentId, List<Long> subjectsIds, Long courseId) {
}