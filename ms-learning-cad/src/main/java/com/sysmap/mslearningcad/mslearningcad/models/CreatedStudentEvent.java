package com.sysmap.mslearningcad.mslearningcad.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreatedStudentEvent {

    private UUID studentId;
    private String fullName;
    private UUID courseId;

    public CreatedStudentEvent(UUID studentId, String firstName, String lastName, UUID courseId) {
        this.studentId = studentId;
        this.fullName = firstName + " " + lastName;
        this.courseId = courseId;
    }


}
