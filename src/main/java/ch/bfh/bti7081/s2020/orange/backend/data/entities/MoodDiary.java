package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoodDiary extends AbstractEntity {

    @ManyToOne
    private Patient patient;

    @OneToMany
    private List<MoodEntry> moodEntries;

}
