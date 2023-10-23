package com.rt.kafkaproducer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MessageProducer {

    @Autowired
    KafkaTemplate<String, Message> kafkaTemplate;

    @Value("${test-event-topic-name}")
    String topic;

    public void sendMessage(String payload, String correlationId) {
        System.out.println("topic->" + topic);
        try {
            Message<String> message = MessageBuilder.withPayload(payload)
                    .setHeader(KafkaHeaders.TOPIC, topic)
                    .setHeader("correlationId", correlationId)
                    .setHeader("date", new Date())
                    .build();

            kafkaTemplate.send(message);

            System.out.println("Message sent successfully");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
