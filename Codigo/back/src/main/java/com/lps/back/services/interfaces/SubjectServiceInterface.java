package com.lps.back.services.interfaces;

import com.lps.back.models.Course;
import com.lps.back.models.Subject;
import java.util.List;

public interface SubjectServiceInterface {
    public Subject get(Long id); // method to get one Subject by id

    public List<Subject> getList(List<Long> ids); // method to get all Subjects

    public void checkSubjectIsAvailable(Subject subject); // method to check if a Subject is available

    public void checkSubjectCurseIsValid(Subject subject, Course course); // method to check if a Subject is valid
}
