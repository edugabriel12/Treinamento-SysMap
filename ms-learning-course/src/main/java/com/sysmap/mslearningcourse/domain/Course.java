package com.sysmap.mslearningcourse.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.UUID;

@Data
@Document(collection = "cursos")
public class Course {

    @MongoId
    private String id;
    private UUID courseId;
    @Indexed(unique = true)
    private String courseName;
    private Boolean status;
    private LocalDateTime createdAt;

    public Course(String courseName) {

        this.courseId = UUID.randomUUID();
        this.courseName = courseName;
        this.status = true;
        this.createdAt = LocalDateTime.now(TimeZone.getTimeZone("America/Sao_Paulo").toZoneId());
    }
}
