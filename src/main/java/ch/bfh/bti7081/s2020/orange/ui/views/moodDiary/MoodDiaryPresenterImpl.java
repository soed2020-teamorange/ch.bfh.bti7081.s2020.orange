package ch.bfh.bti7081.s2020.orange.ui.views.moodDiary;


import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodEntry;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.service.MoodEntryService;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MoodDiaryPresenterImpl implements MoodDiaryPresenter,
    MoodDiaryView.Observer, HasLogger {

  private final MoodDiaryView moodDiaryView;
  private final MoodEntryService moodEntryService;
  private final CurrentUser currentUser;
  private final PatientService patientService;

  @Override
  public void onBeforeEnter() {
    moodDiaryView.setObserver(this);
  }

  @Override
  public void saveNewMoodEntry(MoodEntry me) {
    me.setDiary(((Patient) currentUser.getUser()).getMoodDiary());
    getLogger().info("Save new mood entry [{}]", me.toString());
    moodEntryService.saveNewMoodEntry(me);
  }

  @Override
  public View getView() {
    return moodDiaryView;
  }
}
