package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import ch.bfh.bti7081.s2020.orange.backend.data.Activity;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityEntry extends DiaryEntry {

  @NotNull
  private Activity activity;

  @NotNull
  private LocalDate date;

  @NotNull
  private LocalTime startTime;

  @NotNull
  private LocalTime endTime;
}
