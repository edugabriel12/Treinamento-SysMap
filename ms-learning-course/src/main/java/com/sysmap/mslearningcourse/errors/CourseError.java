package com.sysmap.mslearningcourse.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CourseError {

    static final String COURSE_NOT_EXISTS = "Esse nome de curso não está cadastrado no sistema, verifique se foi" +
            "escrito corretamente!";

    public static ResponseEntity<CustomTypeError> errorCourseNotExists() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(CourseError.COURSE_NOT_EXISTS),
                HttpStatus.NOT_FOUND);
    }

}
