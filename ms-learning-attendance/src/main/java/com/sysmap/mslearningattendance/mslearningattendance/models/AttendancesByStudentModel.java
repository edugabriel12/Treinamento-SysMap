package com.sysmap.mslearningattendance.mslearningattendance.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AttendancesByStudentModel {

    private String fullName;
    private String courseName;
    private List<AttendanceResponseModel> attendances;
}
