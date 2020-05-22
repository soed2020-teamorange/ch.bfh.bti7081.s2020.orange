package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import ch.bfh.bti7081.s2020.orange.backend.data.Mood;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoodEntry extends DiaryEntry {

  @ToString.Exclude
  @ManyToOne
  private MoodDiary diary;

  @NotNull
  private Mood mood;

  @NotNull
  private LocalDate date;

  @NotNull
  private LocalTime time;

  @PositiveOrZero
  private double sleepHours;

  @PositiveOrZero
  private double waterDrunk;

}

