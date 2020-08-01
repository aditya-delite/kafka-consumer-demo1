package com.aditya;

import com.aditya.model.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaConsumerDemo1Application {

    @Value("${topic-name}")
    private String topicName;

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerDemo1Application.class, args);
    }

    @KafkaListener(topics = "kafka-producer-demo1", groupId = "CUG-demo1")
    public void listen(String message) throws JsonProcessingException {
        System.out.println("Received Message in group: " + message);
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(message, Account.class);
        System.out.println(account);
    }
}
