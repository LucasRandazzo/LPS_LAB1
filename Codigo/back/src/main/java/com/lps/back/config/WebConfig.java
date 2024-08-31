package com.lps.back.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.hpsf.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lps.back.models.Course;
import com.lps.back.models.Curriculum;
import com.lps.back.models.Discipline;
import com.lps.back.models.Registration;
import com.lps.back.models.Student;
import com.lps.back.models.Subject;
import com.lps.back.models.Teacher;
import com.lps.back.repositories.CourseRepository;
import com.lps.back.repositories.CurriculumRepository;
import com.lps.back.repositories.DisciplineRepository;
import com.lps.back.repositories.StudentRepository;
import com.lps.back.repositories.TeacherRepository;
import com.lps.back.services.EmailSenderService;
import com.lps.back.services.SubjectService;
import com.lps.back.utils.SubjectSituationEnum;

@Configuration
@EnableWebMvc
@Component
public class WebConfig implements WebMvcConfigurer, CommandLineRunner {
        @Autowired
        private CurriculumRepository curriculumRepository;

        @Autowired
        private DisciplineRepository disciplineRepository;

        @Autowired
        private TeacherRepository teacherRepository;

        @Autowired
        private StudentRepository studentRepository;;

        @Autowired
        private CourseRepository courseRepository;

        @Autowired
        SubjectService subjectService;

        @Autowired
        EmailSenderService emailSenderService;

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
        }

        @Override
        public void run(String... args) throws Exception {
                Teacher teacher = new Teacher(null, "teacher", "teacher@gmail.com",
                                SecurityConfig.passwordEncoder().encode("password"), new ArrayList<Subject>());

                teacherRepository.save(teacher);
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

                Student student1 = new Student(null, "student1", "gmail3",
                                SecurityConfig.passwordEncoder().encode("senha"),
                                new ArrayList<Registration>());

                Student student2 = new Student(null, "student2", "gmail2",
                                SecurityConfig.passwordEncoder().encode("senha"),
                                new ArrayList<Registration>());

                Student student3 = new Student(null, "Pedro Henrique", "pedrohenriquepr08@gmail.com",
                                SecurityConfig.passwordEncoder().encode("senha"),
                                new ArrayList<Registration>());

                studentRepository.saveAll(Arrays.asList(student, student1, student2, student3));

                emailSenderService.sendRecoveryPasswordMail("12321asdas@gmail.com","12321321");
        }
}