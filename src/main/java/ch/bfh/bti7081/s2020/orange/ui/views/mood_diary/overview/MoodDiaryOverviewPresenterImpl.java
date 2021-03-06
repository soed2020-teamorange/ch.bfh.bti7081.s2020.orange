package ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.overview;

import ch.bfh.bti7081.s2020.orange.backend.service.PatientMoodDiaryService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MoodDiaryOverviewPresenterImpl implements MoodDiaryOverviewPresenter {

  private final MoodDiaryOverviewView view;
  private final PatientMoodDiaryService moodDiaryService;

  @Override
  public void onBeforeEnter() {
    this.view.setEntries(this.moodDiaryService.getEntries());
  }

  @Override
  public View getView() {
    return this.view;
  }
}
