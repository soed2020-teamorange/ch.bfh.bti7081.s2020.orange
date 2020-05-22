package ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.create_entry;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodEntry;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientMoodDiaryService;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MoodDiaryCreateEntryPresenterImpl implements MoodDiaryCreateEntryPresenter,
    MoodDiaryCreateEntryView.Observer, HasLogger {

  private final MoodDiaryCreateEntryView moodDiaryCreateEntryView;
  private final PatientMoodDiaryService patientMoodDiaryService;

  @Override
  public void onBeforeEnter() {
    moodDiaryCreateEntryView.setObserver(this);
  }

  @Override
  public void saveNewMoodEntry(MoodEntry me) {
    getLogger().info("Save new mood entry [{}]", me.toString());
    patientMoodDiaryService.addEntry(me);
  }

  @Override
  public View getView() {
    return moodDiaryCreateEntryView;
  }
}
