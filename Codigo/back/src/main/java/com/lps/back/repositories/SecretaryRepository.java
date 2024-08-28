package com.lps.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.back.models.Secretary;

@Repository
public interface SecretaryRepository extends JpaRepository<Secretary, Long> {

}
