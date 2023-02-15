package com.sysmap.mslearningcad.mslearningcad.models;

import lombok.Getter;

import java.util.Date;

@Getter
public class CreateStudentResponse {

    private String fullName;
    private String document;
    private Date birthDate;
    private String courseName;
    private Boolean isActive;

    public CreateStudentResponse(String firstName, String lastName,
                                 String document, Date birthDate,
                                 String courseName) {
        this.fullName = firstName + " " + lastName;
        this.document = document;
        this.birthDate = birthDate;
        this.courseName = courseName;
        this.isActive = true;
    }
}
