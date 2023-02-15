package com.sysmap.mslearningattendance.mslearningattendance.data;

import com.sysmap.mslearningattendance.mslearningattendance.domain.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AttendanceRepository extends CrudRepository<Attendance, String> {

    List<Attendance> findAttendancesByStudentId(UUID studentId);
}
