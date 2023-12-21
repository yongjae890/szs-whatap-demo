package com.whatap.demo.service;

import com.whatap.demo.r2dbc.AsyncKafkaEntity;
import com.whatap.demo.r2dbc.AsyncKafkaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.sender.SenderRecord;

@Service
@RequiredArgsConstructor
public class AsnycKafkaService {

  private final ReactiveKafkaProducerTemplate<String, String> producerTemplate;
  private final ReactiveKafkaConsumerTemplate<String, String> consumerTemplate;
  private final AsyncKafkaRepository asyncKafkaRepository;

  public Flux<SenderRecord<String, String, Void>> sendReactiveToKafka(String topic, String message) {
    ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
    SenderRecord<String, String, Void> senderRecord = SenderRecord.create(record, null);

    Mono<AsyncKafkaEntity> saveEntityMono = Mono.just(AsyncKafkaEntity.builder()
            .status("ReactiveKafkaSend")
            .build())
        .flatMap(asyncKafkaRepository::save);

    Flux<SenderRecord<String, String, Void>> sendRecordFlux = Flux.just(senderRecord)
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(rec -> producerTemplate.send(rec).subscribe());

    return Flux.from(saveEntityMono).thenMany(sendRecordFlux);
  }

  public Flux<ConsumerRecord<String, String>> receiveReactiveFromKafka(String topic) {
    Mono<AsyncKafkaEntity> saveEntityMono = Mono.just(AsyncKafkaEntity.builder()
            .status("ReactiveKafkaConsumer")
            .build())
        .flatMap(asyncKafkaRepository::save);

    Flux<ConsumerRecord<String, String>> kafkaMessageFlux = consumerTemplate.receiveAutoAck()
        .filter(record -> record.topic().equals(topic));

    return saveEntityMono.thenMany(kafkaMessageFlux);
  }

}
