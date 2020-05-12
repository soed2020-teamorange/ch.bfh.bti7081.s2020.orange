package ch.bfh.bti7081.s2020.orange.backend.data.entities;

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
public class MoodEntry extends DiaryEntry {

  @NotNull
  private Mood mood;

  private double sleepHours;

  @NotNull
  private LocalDateTime dateTime;
}
