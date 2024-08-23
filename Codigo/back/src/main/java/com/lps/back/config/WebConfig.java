package com.lps.back.config;

import org.apache.poi.hpsf.Array;
import org.hibernate.mapping.List;
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
import com.lps.back.repositories.SubjectRepository;
import com.lps.back.repositories.TeacherRepository;
import com.lps.back.services.RegistrationService;
import com.lps.back.services.SubjectService;
import com.lps.back.utils.SubjectSituationEnum;

import io.swagger.v3.core.util.Json;

import java.util.ArrayList;

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

                disciplineRepository.save(discipline);

                ArrayList<Teacher> teachers = new ArrayList<Teacher>();
                teachers.add(teacher);
                Subject subject = new Subject(null, 100.0, SubjectSituationEnum.Available, teachers,
                                discipline, new ArrayList<Registration>());
                Subject subject1 = new Subject(null, 100.0, SubjectSituationEnum.Available, teachers,
                                discipline, new ArrayList<Registration>());
                Subject subject2 = new Subject(null, 100.0, SubjectSituationEnum.Available, teachers,
                                discipline, new ArrayList<Registration>());
                Subject subject3 = new Subject(null, 100.0, SubjectSituationEnum.Available, teachers,
                                discipline, new ArrayList<Registration>());
                subjectService.save(subject);
                subjectService.save(subject1);
                subjectService.save(subject2);
                subjectService.save(subject3);

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

                ArrayList<Long> subjectsIds = new ArrayList<Long>();
                subjectsIds.add(subject.getId());
                subjectsIds.add(subject1.getId());
                subjectsIds.add(subject2.getId());
                subjectsIds.add(subject3.getId());
                ArrayList<Subject> subjects = new ArrayList<Subject>();
                subjects.add(subject);
                subjects.add(subject1);
                subjects.add(subject2);
                subjects.add(subject3);

        }
}