package com.sysmap.mslearningcad.mslearningcad.models;

import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class CreateStudentInput {

    private String firstName;
    private String lastName;
    private String document;
    private Date birthDate;
    private UUID courseId;
}
