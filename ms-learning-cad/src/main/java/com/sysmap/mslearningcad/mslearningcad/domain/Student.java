package com.sysmap.mslearningcad.mslearningcad.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

@Data
@Document(collection = "estudantes")
public class Student {

    @MongoId
    private String id;
    private UUID studentId;
    private String firstName;
    private String lastName;
    private String document;
    private Date birthDate;
    private UUID courseId;
    private Boolean status;
    private LocalDateTime createdOn;

    public Student(String firstName, String lastName, String document, Date birthDate, UUID courseId) {
        this.studentId = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
        this.birthDate = birthDate;
        this.courseId = courseId;
        this.status = true;
        this.createdOn = LocalDateTime.now(TimeZone.getTimeZone("America/Sao_Paulo").toZoneId());
    }
}
