package com.lps.back.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lps.back.dtos.user.UserRegisterDTO;
import com.lps.back.models.Course;
import com.lps.back.models.Curriculum;
import com.lps.back.models.Discipline;
import com.lps.back.models.Registration;
import com.lps.back.models.Secretary;
import com.lps.back.models.Subject;
import com.lps.back.repositories.CourseRepository;
import com.lps.back.repositories.CurriculumRepository;
import com.lps.back.repositories.DisciplineRepository;
import com.lps.back.repositories.SecretaryRepository;
import com.lps.back.services.interfaces.IUserService;
import com.lps.back.utils.UsuarioTypesEnum;

@Configuration
@EnableWebMvc
@Component
public class WebConfig implements WebMvcConfigurer, CommandLineRunner {
        @Autowired
        private CurriculumRepository curriculumRepository;

        @Autowired
        private DisciplineRepository disciplineRepository;

        @Autowired
        private SecretaryRepository secretaryRepository;

        @Autowired
        private CourseRepository courseRepository;

        @Autowired
        private IUserService userService;

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
        }

        @Override
        public void run(String... args) throws Exception {
                userService.register(new UserRegisterDTO("Vinicius Rezende ", "viniciusarantes133@gmail.com",
                                UsuarioTypesEnum.SECRETARY)); // TODO: Alterar para o seu email


                Secretary secretary = new Secretary(null, "secretary", "secretary@gmail.com",
                                SecurityConfig.passwordEncoder().encode("password"));

                secretaryRepository.save(secretary);
                Course course1 = new Course(null, "Software Engineer", new ArrayList<Curriculum>());
                courseRepository.save(course1);

                Curriculum course = new Curriculum(null, course1,
                                new ArrayList<Registration>(),
                                new ArrayList<Discipline>());

                curriculumRepository.save(course);

                ArrayList<Curriculum> courses = new ArrayList<Curriculum>();
                courses.add(course);
                Discipline discipline = new Discipline(null, "Laboratório de Desenvolvimento de Software", 100,
                                new ArrayList<Subject>(),
                                courses);
                Discipline discipline1 = new Discipline(null, "Engenharia de Software ", 100,
                                new ArrayList<Subject>(),
                                courses);

                disciplineRepository.save(discipline);
                disciplineRepository.save(discipline1);

        }
}