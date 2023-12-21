package com.whatap.demo.r2dbc;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsyncKafkaRepository extends ReactiveCrudRepository<AsyncKafkaEntity, Long> {

}
