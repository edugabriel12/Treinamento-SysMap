package com.sysmap.mslearningcad.mslearningcad.services;

import com.sysmap.mslearningcad.mslearningcad.models.CreatedStudentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);
    @Value("${topic.name}")
    private String topic;
    private final KafkaTemplate<String, CreatedStudentEvent> kafkaTemplate;

    @Autowired
    public EventService(KafkaTemplate<String, CreatedStudentEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(CreatedStudentEvent createdStudentEvent) {
        kafkaTemplate.send(topic, createdStudentEvent).addCallback(
                success -> logger.info("Messagem enviada!"),
                failure -> logger.info("Mensagem falhou!")
        );
    }

}
