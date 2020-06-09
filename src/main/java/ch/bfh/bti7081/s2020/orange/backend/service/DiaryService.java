package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Diary;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.DiaryEntry;
import ch.bfh.bti7081.s2020.orange.backend.repositories.DiaryBaseRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class DiaryService<T extends Diary<K>, K extends DiaryEntry> {

  private final CurrentUser currentUser;
  private final DiaryBaseRepository<T> repository;

  public List<K> getEntries() {
    return this.repository.getByPatientId(this.currentUser.getUser().getId()).getEntries();
  }

  public T addEntry(final K entry) {
    final T diary = this.repository.getByPatientId(this.currentUser.getUser().getId());

    diary.addEntry(entry);

    return this.repository.save(diary);
  }
}
