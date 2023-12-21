package com.whatap.demo.controller;


import com.whatap.demo.service.AsnycKafkaService;
import com.whatap.demo.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.SenderRecord;

@RestController
@RequiredArgsConstructor
public class KafkaController {

  private final KafkaService kafkaService;
  private final AsnycKafkaService asnycKafkaService;
  private static final String TOPIC = "testTopic";

  @GetMapping("/send")
  public String sendMessage() {
    kafkaService.sendToKafka(TOPIC, "test");
    return "Message sent to Kafka Topic";
  }

  @GetMapping(value = "/reactive/send", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<SenderRecord<String, String, Void>> sendReactiveMessage() {
    return asnycKafkaService.sendReactiveToKafka(TOPIC, "test");
  }

  @GetMapping(value = "/reactive/receive", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ConsumerRecord<String, String>> receiveReactiveMessage() {
    return asnycKafkaService.receiveReactiveFromKafka(TOPIC);
  }
}
