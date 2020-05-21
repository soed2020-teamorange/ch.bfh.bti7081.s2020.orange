package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoodDiary extends AbstractEntity {

    public MoodDiary(Patient p) {
        this.patient = p;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Patient patient;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "diary")
    private List<MoodEntry> moodEntries;


}
