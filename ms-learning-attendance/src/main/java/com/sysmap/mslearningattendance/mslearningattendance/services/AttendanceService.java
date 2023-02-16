package com.sysmap.mslearningattendance.mslearningattendance.services;

import com.sysmap.mslearningattendance.mslearningattendance.data.AttendanceRepository;
import com.sysmap.mslearningattendance.mslearningattendance.data.StudentRepository;
import com.sysmap.mslearningattendance.mslearningattendance.domain.Attendance;
import com.sysmap.mslearningattendance.mslearningattendance.domain.Student;
import com.sysmap.mslearningattendance.mslearningattendance.exceptions.CourseException;
import com.sysmap.mslearningattendance.mslearningattendance.exceptions.StudentException;
import com.sysmap.mslearningattendance.mslearningattendance.models.AttendanceResponseModel;
import com.sysmap.mslearningattendance.mslearningattendance.models.AttendancesByStudentModel;
import com.sysmap.mslearningattendance.mslearningattendance.models.CourseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository, StudentRepository studentRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }

    public void saveStudentAttendance(UUID courseId, UUID studentId, Boolean attendanceStatus) throws StudentException, CourseException {
        Student student = studentRepository.findStudentByStudentId(studentId).orElseThrow(StudentException::new);
        CourseModel[] course = getCourse(courseId);

        if (course.length == 0 || !student.getCourseId().equals(course[0].getCourseId())) {
            throw new CourseException();
        }

        Attendance attendance = new Attendance(studentId, courseId, attendanceStatus);

        attendanceRepository.save(attendance);
    }

    public AttendancesByStudentModel getAttendancesByStudent(UUID studentId) throws StudentException {
        Student student = studentRepository.findStudentByStudentId(studentId).orElseThrow(StudentException::new);
        List<Attendance> attendances = attendanceRepository.findAttendancesByStudentId(studentId);
        List<AttendanceResponseModel> attendanceResponses = new ArrayList<>();
        CourseModel[] course = getCourse(student.getCourseId());
        ZoneId brazilZone = ZoneId.of("America/Sao_Paulo");

        for (Attendance attendance: attendances) {
            ZonedDateTime brazilZonedTime = ZonedDateTime.ofInstant(attendance.getClassDate().toInstant(), brazilZone);
            attendanceResponses.add(new AttendanceResponseModel(brazilZonedTime.toLocalDateTime(),
                                                                attendance.getAttendanceStatus()));
        }

        AttendancesByStudentModel attendancesByStudentModel = new AttendancesByStudentModel(
                student.getFullName(),
                course[0].getCourseName(),
                attendanceResponses
        );

        return attendancesByStudentModel;
    }

    public CourseModel[] getCourse(UUID courseId) {

        CourseModel[] courseModel = new RestTemplate().getForEntity(
                "http://localhost:8081/api/v1/courses?courseId=" + courseId,
                CourseModel[].class
        ).getBody();

        return courseModel;
    }
}
