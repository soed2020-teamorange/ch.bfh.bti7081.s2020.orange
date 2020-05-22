package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import ch.bfh.bti7081.s2020.orange.backend.data.Activity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
public class ActivityEntry extends DiaryEntry {

  @NotNull
  private Activity type;

  private double duration;
  @NotNull
  private LocalDateTime startTime;
  private LocalDateTime endTime;
}
