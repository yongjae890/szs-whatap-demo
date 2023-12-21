package com.whatap.demo.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  @Column(name = "create_time", nullable = false)
  @CreatedDate
  private LocalDateTime createTime;

  @Column(name = "update_time", nullable = true)
  @LastModifiedDate
  @Setter
  private LocalDateTime updateTime;
}
