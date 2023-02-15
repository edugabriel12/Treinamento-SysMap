package com.sysmap.mslearningcad.mslearningcad.controllers;

import com.sysmap.mslearningcad.mslearningcad.domain.Student;
import com.sysmap.mslearningcad.mslearningcad.errors.StudentError;
import com.sysmap.mslearningcad.mslearningcad.exceptions.StudentException;
import com.sysmap.mslearningcad.mslearningcad.models.*;
import com.sysmap.mslearningcad.mslearningcad.services.EventService;
import com.sysmap.mslearningcad.mslearningcad.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    private final EventService eventService;

    @Autowired
    public StudentController(StudentService studentService, EventService eventService) {
        this.studentService = studentService;
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody CreateStudentInput createStudentInput) {
        Student createdStudent;
        CreatedStudentEvent createdStudentEvent;
        try {
            createdStudent = studentService.createStudent(createStudentInput);
            createdStudentEvent = studentService.createStudentEventModel(createdStudent);
            eventService.send(createdStudentEvent);
        } catch (StudentException ex) {
            return StudentError.errorCourseNotExists();
        }

        CreateStudentResult result = new CreateStudentResult(createdStudent.getStudentId());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable UUID studentId) {
        Student fetchedStudent;
        CourseModel[] fetchedCourse;
        try {
            fetchedStudent = studentService.getById(studentId);
            fetchedCourse = studentService.getCourse(fetchedStudent.getCourseId());
        } catch (StudentException ex) {
            return StudentError.errorStudentNotExists();
        }

        CreateStudentResponse response = new CreateStudentResponse(
                fetchedStudent.getFirstName(),
                fetchedStudent.getLastName(),
                fetchedStudent.getDocument(),
                fetchedStudent.getBirthDate(),
                fetchedCourse[0].getCourseName()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
