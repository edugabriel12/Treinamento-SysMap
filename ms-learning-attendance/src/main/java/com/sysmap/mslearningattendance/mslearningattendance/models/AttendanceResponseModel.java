package com.sysmap.mslearningattendance.mslearningattendance.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AttendanceResponseModel {

    private Date classDate;
    private Boolean attendanceStatus;
}
