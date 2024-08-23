package com.lps.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.back.models.Registration;
import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
   
}
