package org.example.springkafka.service;

import lombok.RequiredArgsConstructor;
import org.example.springkafka.dto.MyMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class KafkaProducerService {

    private static final String TOPIC_NAME = "topic5";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, MyMessage> newKafkaTemplate;

    public void send(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }

    public void sendWithCallback(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, message);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent " + message + " offset: " + result.getRecordMetadata().offset());
            } else {
                System.out.println("Failed " + message + " due to " + ex.getMessage());
            }
        });
    }

    public void sendJson(MyMessage message) {
        newKafkaTemplate.send(TOPIC_NAME, message);
    }
}
