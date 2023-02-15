package com.sysmap.mslearningcad.mslearningcad.data;

import com.sysmap.mslearningcad.mslearningcad.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends MongoRepository<Student, String> {

    Optional<Student> findCourseByStudentId(UUID studentId);
}
