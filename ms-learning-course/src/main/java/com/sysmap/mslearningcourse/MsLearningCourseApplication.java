package com.sysmap.mslearningcourse;

import com.sysmap.mslearningcourse.data.CourseRepository;
import com.sysmap.mslearningcourse.domain.Course;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsLearningCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsLearningCourseApplication.class, args);
	}

}
