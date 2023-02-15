package com.sysmap.mslearningattendance.mslearningattendance.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("student")
@Builder
public class Student {
    @Id
    @Indexed
    private String id;
    private UUID studentId;
    private String fullName;
    private UUID courseId;

    public Student(UUID studentId, String fullName, UUID courseId) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.courseId = courseId;
    }
}
