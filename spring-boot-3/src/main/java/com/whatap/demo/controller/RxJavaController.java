package com.whatap.demo.controller;


import com.whatap.demo.service.RxJavaService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RxJavaController {

  private final RxJavaService rxJavaService;

  @GetMapping("/rxjava")
  public Single<ResponseEntity<String>> getRxJavaData() {
    return rxJavaService.getData()
        .map(ResponseEntity::ok);
  }
}
