package com.whatap.demo.controller;


import com.whatap.demo.service.AsyncService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AsyncController {

  private final AsyncService asyncService;

  @GetMapping("/process")
  public CompletableFuture<String> process() {
    return asyncService.processAsync();
  }
}
