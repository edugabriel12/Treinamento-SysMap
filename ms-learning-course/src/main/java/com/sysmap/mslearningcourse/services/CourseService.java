package com.sysmap.mslearningcourse.services;

import com.sysmap.mslearningcourse.data.CourseRepository;
import com.sysmap.mslearningcourse.domain.Course;
import com.sysmap.mslearningcourse.exceptions.CourseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public UUID createCourse(String courseName) {
        Course course = new Course(courseName);

        return courseRepository.save(course).getCourseId();
    }

    public List<Course> searchCourses(String courseName) throws CourseException {
        List<Course> courses = new ArrayList<>();

        if (courseName == null) {
            courses = courseRepository.findAll();
        } else {
           Course course = courseRepository.findCourseByCourseName(courseName).orElseThrow(CourseException::new);
           courses.add(course);
        }

        return courses;
    }
}
