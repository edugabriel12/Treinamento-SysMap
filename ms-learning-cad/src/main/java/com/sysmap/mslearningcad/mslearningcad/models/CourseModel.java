package com.sysmap.mslearningcad.mslearningcad.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CourseModel {

    private static final Long serialVersionUID = 1L;

    private UUID courseId;
    private String courseName;
    private Boolean status;
    private LocalDateTime createdOn;
}
