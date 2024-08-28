package com.lps.back.config;

import java.util.ArrayList;

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
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.models.Teacher;
import com.lps.back.repositories.CourseRepository;
import com.lps.back.repositories.DisciplineRepository;
import com.lps.back.repositories.StudentRepository;
import com.lps.back.repositories.TeacherRepository;
import com.lps.back.services.RegistrationService;
import com.lps.back.services.SubjectService;
import com.lps.back.utils.SubjectSituationEnum;

@Configuration
@EnableWebMvc
@Component
public class WebConfig implements WebMvcConfigurer, CommandLineRunner {
        @Autowired
        private CourseRepository courseRepository;

        @Autowired
        private DisciplineRepository disciplineRepository;

        @Autowired
        private TeacherRepository teacherRepository;

        @Autowired
        private StudentRepository studentRepository;;

        @Autowired
        private RegistrationService registrationService;

        @Autowired
        SubjectService subjectService;

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
        }

        @Override
        public void run(String... args) throws Exception {
                Teacher teacher = new Teacher(null, "teacher", "teacher@gmail.com",
                                SecurityConfig.passwordEncoder().encode("password"), new ArrayList<Subject>());

                teacherRepository.save(teacher);

                Course course = new Course(null, "Software Engineer", 100, new ArrayList<Registration>(),
                                new ArrayList<Discipline>());

                courseRepository.save(course);

                ArrayList<Course> courses = new ArrayList<Course>();
                courses.add(course);
                Discipline discipline = new Discipline(null, "Laboratório de Desenvolvimento de Software", 100, 500.0,
                                new ArrayList<Subject>(),
                                courses);
                Discipline discipline1 = new Discipline(null, "Engenharia de Software ", 100, 500.0,
                                new ArrayList<Subject>(),
                                courses);

                disciplineRepository.save(discipline);
                disciplineRepository.save(discipline1);

                ArrayList<Teacher> teachers = new ArrayList<Teacher>();
                teachers.add(teacher);

                for (int i = 0; i < 10; i++) {
                        if (i / 2 == 0) {
                                Subject subject = new Subject(null, 100.0, SubjectSituationEnum.Available, teachers,
                                                discipline,
                                                new ArrayList<Registration>());
                                subjectService.save(subject);
                        } else {
                                Subject subject = new Subject(null, 100.0, SubjectSituationEnum.Available, teachers,
                                                discipline1,
                                                new ArrayList<Registration>());
                                subjectService.save(subject);
                        }
                }

                Student student = new Student(null, "student", "gmail",
                                SecurityConfig.passwordEncoder().encode("senha"),
                                new ArrayList<Registration>());
                Student student2 = new Student(null, "student2", "gmail2",
                                SecurityConfig.passwordEncoder().encode("senha"),
                                new ArrayList<Registration>());

                Student student1 = new Student(null, "student1", "gmail3",
                                SecurityConfig.passwordEncoder().encode("senha"),
                                new ArrayList<Registration>());

                studentRepository.save(student);
                studentRepository.save(student1);
                studentRepository.save(student2);

        }
}