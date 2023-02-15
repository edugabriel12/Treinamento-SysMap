package com.sysmap.mslearningcad.mslearningcad.models;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateStudentResult {

    private UUID studentId;

    public CreateStudentResult(UUID studentId) {
        this.studentId = studentId;
    }
}
