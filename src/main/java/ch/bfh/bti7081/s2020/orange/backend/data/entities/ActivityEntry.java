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
public class ActivityEntry extends AbstractEntity {

    @ToString.Exclude
    @ManyToOne
    private ActivityDiary diary;

    @NotNull
    private Activity activity;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotBlank
    @Size(max = 1000)
    private String description;

}
