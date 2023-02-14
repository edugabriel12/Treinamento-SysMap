package com.sysmap.mslearningcourse.data;

import com.sysmap.mslearningcourse.domain.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CourseRepository extends MongoRepository<Course, String> {

    Optional<Course> findCourseByCourseName(String courseName);
}
