package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@EqualsAndHashCode
public abstract class AbstractEntity {

  @Id
  @GeneratedValue
  Long id;

  @CreatedDate
  LocalDateTime createdDate;
  @LastModifiedDate
  LocalDateTime modifiedDate;
}
