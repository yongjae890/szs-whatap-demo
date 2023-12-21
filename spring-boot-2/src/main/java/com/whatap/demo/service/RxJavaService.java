package com.whatap.demo.service;

import com.whatap.demo.jpa.RxjavaEntity;
import com.whatap.demo.jpa.RxjavaRepository;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RxJavaService {

  private final RxjavaRepository rxjavaRepository;

  public Single<String> getData() {
    return Single
        .fromCallable(() -> {
          RxjavaEntity entity = RxjavaEntity.builder()
              .status("RxJava")
              .build();
          rxjavaRepository.save(entity);
          return "RxJava Data";
        })
        .subscribeOn(Schedulers.io())
        .delay(1, TimeUnit.SECONDS)
        .observeOn(Schedulers.computation());
  }
}
