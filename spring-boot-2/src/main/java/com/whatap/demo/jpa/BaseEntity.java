package com.whatap.demo.jpa;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
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
