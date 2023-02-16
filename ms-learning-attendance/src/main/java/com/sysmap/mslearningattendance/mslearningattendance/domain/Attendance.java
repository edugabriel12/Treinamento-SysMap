package com.sysmap.mslearningattendance.mslearningattendance.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

@Data
@NoArgsConstructor
@RedisHash("attendance")
public class Attendance {

    @Id
    @Indexed
    private String id;
    private UUID attendanceId;
    @Indexed
    private UUID studentId;
    private UUID courseId;
    private Date classDate;
    private Boolean attendanceStatus;

    public Attendance(UUID studentId, UUID courseId, Boolean attendanceStatus) {
        this.attendanceId = UUID.randomUUID();
        this.studentId = studentId;
        this.courseId = courseId;
        this.classDate = new Date();
        this.attendanceStatus = attendanceStatus;
    }
}
