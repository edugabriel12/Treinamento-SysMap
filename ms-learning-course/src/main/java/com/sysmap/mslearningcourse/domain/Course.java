package com.sysmap.mslearningcourse.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collection = "cursos")
public class Course {

    @MongoId
    private String id;
    private UUID courseId;
    private String courseName;
    private Boolean status;
    private LocalDateTime createdAt;

    public Course create(String courseName) {
        Course course = new Course();

        course.courseId = UUID.randomUUID();
        course.courseName = courseName;
        course.status = true;
        course.createdAt = LocalDateTime.now();

        return course;
    }
}
