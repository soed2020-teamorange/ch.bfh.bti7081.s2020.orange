package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.ActivityDiary;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.ActivityEntry;
import ch.bfh.bti7081.s2020.orange.backend.repositories.ActivityDiaryRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PatientActivityDiaryService extends DiaryService<ActivityDiary, ActivityEntry> {

  public PatientActivityDiaryService(CurrentUser currentUser, ActivityDiaryRepository repository) {
    super(currentUser, repository);
  }
}
