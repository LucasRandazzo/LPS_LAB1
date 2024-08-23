package com.lps.back.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lps.back.models.Course;
import com.lps.back.models.Discipline;
import com.lps.back.models.Registration;
import com.lps.back.models.Secretary;
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.models.Teacher;
import com.lps.back.repositories.SecretaryRepository;
import com.lps.back.utils.SubjectSituationEnum;

import java.util.ArrayList;

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
                Secretary secretary = new Secretary(null, "secretary", "gmail,",
                                SecurityConfig.passwordEncoder().encode("senha"));

                Teacher teacher = new Teacher(null, "teacher", "gmail",
                                SecurityConfig.passwordEncoder().encode("senha"), new ArrayList<Subject>());

                Discipline discipline = new Discipline(null, "discipline", 100, 100.0, new ArrayList<Subject>(),
                                new ArrayList<Course>());

                Subject subject = new Subject(null, 100.0, SubjectSituationEnum.Waiting, new ArrayList<Teacher>(),
                                discipline, new ArrayList<Registration>());

                Student student = new Student(null, "student", "gmail",
                                SecurityConfig.passwordEncoder().encode("senha"),
                                new ArrayList<Registration>());

                Course course = new Course(null, "course", 100, new ArrayList<Registration>(),
                                new ArrayList<Discipline>());

                Registration registration = new Registration(null, student, course, new ArrayList<Subject>());

                student.addRegistration(registration);
        }
}