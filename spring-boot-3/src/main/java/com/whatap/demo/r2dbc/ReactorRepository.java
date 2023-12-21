package com.whatap.demo.r2dbc;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactorRepository extends ReactiveCrudRepository<ReactorEntity, Long> {

}
