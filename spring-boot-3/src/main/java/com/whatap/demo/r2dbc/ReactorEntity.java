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
@Table(name = "reactor")
public class ReactorEntity extends BaseEntity {

  @Id
  private Long id;

  @Column("status")
  private String status;

  @Builder
  public ReactorEntity(String status) {
    this.status = status;
  }
}