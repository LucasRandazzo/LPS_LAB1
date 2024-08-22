package com.lps.back.config;

import com.lps.back.config.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lps.back.models.Secretary;
import com.lps.back.models.Usuario;
import com.lps.back.repositories.SecretaryRepository;

@Configuration
@EnableWebMvc
@Component
public class WebConfig implements WebMvcConfigurer, CommandLineRunner {
        @Autowired
        private SecretaryRepository secretaryRepository;

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
        }

        @Override
        public void run(String... args) throws Exception {
              Secretary secretary = new Secretary();
              secretary.setId(null);
              secretary.setName("Secretaria");
              secretary.setMail("vivia@gmail.com");
              secretary.setPassword(SecurityConfig.passwordEncoder().encode("senha"));
              secretaryRepository.save(secretary);
        }}