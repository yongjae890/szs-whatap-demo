package com.whatap.demo.service;

import com.whatap.demo.r2dbc.ReactorEntity;
import com.whatap.demo.r2dbc.ReactorRepository;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactorService {

  private final ReactorRepository reactorRepository;

  public Mono<String> getData() {
    return Mono.just(ReactorEntity.builder()
            .status("Reactor")
            .build())
        .flatMap(reactorRepository::save)
        .then(Mono.just("Reactor Data").delayElement(Duration.ofSeconds(1)));
  }

}
