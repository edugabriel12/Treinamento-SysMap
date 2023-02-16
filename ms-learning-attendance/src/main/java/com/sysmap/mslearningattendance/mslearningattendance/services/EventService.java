package com.sysmap.mslearningattendance.mslearningattendance.services;

import com.sysmap.mslearningattendance.mslearningattendance.data.StudentRepository;
import com.sysmap.mslearningattendance.mslearningattendance.domain.Student;
import com.sysmap.mslearningattendance.mslearningattendance.models.CreatedStudentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class EventService {

    private final StudentRepository studentRepository;

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    public EventService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @KafkaListener(topics = "students", containerFactory = "jsonKafkaListenerContainerFactory")
    public void receive(@Payload CreatedStudentEvent createdStudentEvent) {
        Student student = new Student(
                createdStudentEvent.getStudentId(),
                createdStudentEvent.getFullName(),
                createdStudentEvent.getCourseId()
        );

        studentRepository.save(student);
        logger.info("Mensagem consumida: " + student);

    }
}
