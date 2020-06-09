package ch.bfh.bti7081.s2020.orange.ui.views.activity_diary.overview;

import ch.bfh.bti7081.s2020.orange.backend.service.PatientActivityDiaryService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ActivityDiaryOverviewPresenterImpl implements ActivityDiaryOverviewPresenter {

  private final ActivityDiaryOverviewView view;
  private final PatientActivityDiaryService activityDiaryService;

  @Override
  public void onBeforeEnter() {
    this.view.setEntries(this.activityDiaryService.getEntries());
  }

  @Override
  public View getView() {
    return this.view;
  }
}
