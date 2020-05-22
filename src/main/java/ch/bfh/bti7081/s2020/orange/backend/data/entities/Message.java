package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import ch.bfh.bti7081.s2020.orange.backend.data.MessageState;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message extends AbstractEntity {

  private String content;
  // TODO by auditing
  private LocalDateTime creationDate;
  private MessageState state;

  @ManyToOne
  private User sender;

  @ManyToOne
  private Chat chat;
}
