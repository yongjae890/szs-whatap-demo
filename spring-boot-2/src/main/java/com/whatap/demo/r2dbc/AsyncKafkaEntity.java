package com.whatap.demo.r2dbc;

import com.whatap.demo.jpa.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@NoArgsConstructor
@Table(name = "async_kafka")
public class AsyncKafkaEntity extends BaseEntity {

  @Id
  private Long id;

  @Column("status")
  private String status;

  @Builder
  public AsyncKafkaEntity(String status) {
    this.status = status;
  }
}

