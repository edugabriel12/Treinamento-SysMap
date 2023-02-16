package com.sysmap.mslearningattendance.mslearningattendance.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AttendanceResponseModel {

    private LocalDateTime classDate;
    private Boolean attendanceStatus;
}
