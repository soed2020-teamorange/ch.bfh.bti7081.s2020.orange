package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class ActivityDiary extends Diary<ActivityEntry> {

}
