package com.whatap.demo.service;


import com.whatap.demo.jpa.AsyncEntity;
import com.whatap.demo.jpa.AsyncRepository;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

  private final AsyncRepository asyncRepository;

  @Async
  public CompletableFuture<String> processAsync() {
    return CompletableFuture.supplyAsync(() -> {
      try {
        asyncRepository.save(AsyncEntity.builder()
            .status("async")
            .build());
        Thread.sleep(5000); // 5초 대기
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      return "Completed";
    });
  }
}
