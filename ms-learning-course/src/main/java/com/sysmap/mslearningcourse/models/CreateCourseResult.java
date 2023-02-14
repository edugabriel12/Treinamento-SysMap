package com.sysmap.mslearningcourse.models;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateCourseResult {

    private UUID courseId;

    public CreateCourseResult(UUID courseId) {
        this.courseId = courseId;
    }

}
