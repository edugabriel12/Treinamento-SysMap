package com.sysmap.mslearningcourse.controllers;

import com.sysmap.mslearningcourse.domain.Course;
import com.sysmap.mslearningcourse.errors.CourseError;
import com.sysmap.mslearningcourse.exceptions.CourseException;
import com.sysmap.mslearningcourse.models.CreateCourseResult;
import com.sysmap.mslearningcourse.models.CreateStudentInput;
import com.sysmap.mslearningcourse.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody @Valid CreateStudentInput studentInput) {
        UUID createdCourseId = courseService.createCourse(studentInput.getCourseName());

        CreateCourseResult response = new CreateCourseResult(createdCourseId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> searchCourses(@RequestParam(required = false) String courseName) {
        List<Course> courses;
        try {
            courses = courseService.searchCourses(courseName);
        } catch (CourseException ex) {
            return CourseError.errorCourseNotExists();
        }

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
