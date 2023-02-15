package com.sysmap.mslearningattendance.mslearningattendance.data;

import com.sysmap.mslearningattendance.mslearningattendance.domain.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends CrudRepository<Student, String> {

    Optional<Student> findStudentByStudentId(UUID studentId);
}
