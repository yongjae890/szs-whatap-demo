package com.whatap.demo.service;

import com.whatap.demo.jpa.KafkaEntity;
import com.whatap.demo.jpa.KafkaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final KafkaRepository kafkaRepository;

  public void sendToKafka(String topic, String message) {
    kafkaRepository.save(KafkaEntity.builder()
        .status("kafkaSend")
        .build());
    kafkaTemplate.send(topic, message);
  }

  @KafkaListener(topics = "testTopic")
  public void listen(String message) {
    // Process the message here
    System.out.println("Received message in group YourGroupId: " + message);
    kafkaRepository.save(KafkaEntity.builder()
        .status("Received")
        .build());
  }
}
