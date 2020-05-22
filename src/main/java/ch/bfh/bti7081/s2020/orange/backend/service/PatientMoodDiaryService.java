package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodDiary;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodEntry;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MoodDiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PatientMoodDiaryService {

  private final CurrentUser currentUser;
  private final MoodDiaryRepository moodDiaryRepository;

  public MoodDiary addEntry(MoodEntry entry) {
    MoodDiary diary = moodDiaryRepository.getByPatientId(currentUser.getUser().getId());

    diary.addEntry(entry);

    return moodDiaryRepository.save(diary);
  }
}
