package com.sysmap.mslearningcad.mslearningcad.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StudentError {

    static final String STUDENT_NOT_EXISTS = "Esse estudante não existe no sistema, verifique se" +
            "o identificador passado realmente é válido!";

    static final String COURSE_NOT_EXISTS = "o Id do curso do estudante passado" +
            " não existe no sistema, verifique se" +
            "o identificador passado realmente é válido!";

    public static ResponseEntity<CustomTypeError> errorStudentNotExists() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(StudentError.STUDENT_NOT_EXISTS),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomTypeError> errorCourseNotExists() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(StudentError.COURSE_NOT_EXISTS),
                HttpStatus.NOT_FOUND);
    }
}
