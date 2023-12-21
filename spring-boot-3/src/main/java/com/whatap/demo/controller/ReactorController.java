package com.whatap.demo.controller;


import com.whatap.demo.service.ReactorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ReactorController {

  private final ReactorService reactorService;

  @GetMapping("/reactor")
  public Mono<String> getReactorData() {
    return reactorService.getData();
  }
}
