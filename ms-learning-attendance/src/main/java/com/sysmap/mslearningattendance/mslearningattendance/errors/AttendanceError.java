package com.sysmap.mslearningattendance.mslearningattendance.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AttendanceError {

    static final String STUDENT_NOT_EXISTS = "Esse estudante não existe no sistema, verifique se" +
            "o identificador passado realmente é válido!";

    static final String COURSE_NOT_ASSOCIATED_WITH_STUDENT = "o Id do curso passado" +
            " não está associado com esse estudante no sistema, verifique se" +
            "o identificador passado realmente é válido!";

    public static ResponseEntity<CustomTypeError> errorStudentNotExists() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(AttendanceError.STUDENT_NOT_EXISTS),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomTypeError> errorCourseNotAssociatedWithStudent() {
        return new ResponseEntity<CustomTypeError>(new CustomTypeError(AttendanceError.COURSE_NOT_ASSOCIATED_WITH_STUDENT),
                HttpStatus.CONFLICT);
    }
}
