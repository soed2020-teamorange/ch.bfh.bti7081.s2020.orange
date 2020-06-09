package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class Diary<T extends DiaryEntry> extends AbstractEntity {

  @OneToOne
  private Patient patient;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<T> entries;

  public void addEntry(final T entry) {
    entries.add(entry);
  }
}
