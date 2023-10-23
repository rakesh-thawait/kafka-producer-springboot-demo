package com.rt.kafkaproducer.controller;

import com.rt.kafkaproducer.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class MessageController {

    @Autowired
    MessageProducer messageProducer;
    @PostMapping("/book")
    public ResponseEntity<String> getMessage(@RequestBody String message) {
        messageProducer.sendMessage(message, UUID.randomUUID().toString());
        return ResponseEntity.ok(message);
    }
}
