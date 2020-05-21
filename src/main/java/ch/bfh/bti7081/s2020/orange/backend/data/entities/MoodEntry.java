package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoodEntry extends AbstractEntity {

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
