package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import ch.bfh.bti7081.s2020.orange.backend.data.Mood;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoodEntry extends DiaryEntry {

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

  public MoodEntry(String title, String content, Mood mood, LocalDate date, LocalTime time,
      double sleepHours, double waterDrunk) {
    super(title, content);

    this.mood = mood;
    this.date = date;
    this.time = time;
    this.sleepHours = sleepHours;
    this.waterDrunk = waterDrunk;

  }

}

