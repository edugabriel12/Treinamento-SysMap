package com.sysmap.mslearningcad.mslearningcad.services;

import com.sysmap.mslearningcad.mslearningcad.data.StudentRepository;
import com.sysmap.mslearningcad.mslearningcad.domain.Student;
import com.sysmap.mslearningcad.mslearningcad.exceptions.StudentException;
import com.sysmap.mslearningcad.mslearningcad.models.CourseModel;
import com.sysmap.mslearningcad.mslearningcad.models.CreateStudentInput;
import com.sysmap.mslearningcad.mslearningcad.models.CreatedStudentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(CreateStudentInput studentInput) throws StudentException {
        CourseModel[] courseModels = getCourse(studentInput.getCourseId());

        if (courseModels[0] == null) {
            throw new StudentException();
        }

        Student student = new Student(
                studentInput.getFirstName(),
                studentInput.getLastName(),
                studentInput.getDocument(),
                studentInput.getBirthDate(),
                studentInput.getCourseId()
        );

        return studentRepository.save(student);
    }

    public CreatedStudentEvent createStudentEventModel(Student student) {
       CreatedStudentEvent createdStudentEvent = new CreatedStudentEvent(
               student.getStudentId(),
               student.getFirstName(),
               student.getLastName(),
               student.getCourseId()
       );

       return createdStudentEvent;
    }

    public Student getById(UUID studentId) throws StudentException {
        Student fetchedStudent = studentRepository.findCourseByStudentId(studentId).orElseThrow(StudentException::new);

        return fetchedStudent;
    }

    public CourseModel[] getCourse(UUID courseId) {

        CourseModel[] courseModel = new RestTemplate().getForEntity(
                "http://localhost:8082/api/v1/courses?courseId=" + courseId,
                CourseModel[].class
        ).getBody();

        return courseModel;
    }
}
