package com.sysmap.mslearningattendance.mslearningattendance.controllers;

import com.sysmap.mslearningattendance.mslearningattendance.errors.AttendanceError;
import com.sysmap.mslearningattendance.mslearningattendance.exceptions.CourseException;
import com.sysmap.mslearningattendance.mslearningattendance.exceptions.StudentException;
import com.sysmap.mslearningattendance.mslearningattendance.models.AttendancesByStudentModel;
import com.sysmap.mslearningattendance.mslearningattendance.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/attendances")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping(value="/student/{studentId}")
    public ResponseEntity<?> getAttendancesByStudent(@PathVariable UUID studentId) {
        AttendancesByStudentModel attendancesByStudent;
        try {
            attendancesByStudent = attendanceService.getAttendancesByStudent(studentId);
        } catch (StudentException e) {
            return AttendanceError.errorStudentNotExists();
        }

        return new ResponseEntity<>(attendancesByStudent, HttpStatus.OK);
    }

    @PostMapping(value="/course/{courseId}/student/{studentId}")
    public ResponseEntity<?> registerStudentAttendance(@PathVariable UUID courseId,
                                                       @PathVariable UUID studentId,
                                                       @RequestBody Boolean attendanceStatus) {
        try {
            attendanceService.saveStudentAttendance(courseId, studentId, attendanceStatus);
        } catch (StudentException e) {
            return AttendanceError.errorStudentNotExists();
        } catch (CourseException e) {
            return AttendanceError.errorCourseNotAssociatedWithStudent();
        }

        return new ResponseEntity<>("Presença salva com sucesso!", HttpStatus.OK);
    }
}
