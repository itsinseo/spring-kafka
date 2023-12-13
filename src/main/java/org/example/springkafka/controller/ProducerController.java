package org.example.springkafka.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.springkafka.dto.MyMessage;
import org.example.springkafka.service.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/producer")
@RestController
public class ProducerController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/publish")
    public String publish(@RequestParam String message) {
        kafkaProducerService.send(message);
        return "published message: " + message;
    }

    @Operation(summary = "publishWithCallback")
    @PostMapping("/publish2")
    public String publishWithCallback(@RequestParam String message) {
        kafkaProducerService.sendWithCallback(message);
        return "published message: " + message;
    }

    // TIL로 @RequestMapping, parameter 자동 매핑에 대해 작성함
    @Operation(summary = "publishJson")
    @RequestMapping("/publish3")
    public String publishJson(MyMessage message) {
        kafkaProducerService.sendJson(message);
        return "published message: " + "name: " + message.getName() + ", message: " + message.getMessage();
    }
}
