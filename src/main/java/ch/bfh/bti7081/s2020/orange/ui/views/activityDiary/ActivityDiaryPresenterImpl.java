package ch.bfh.bti7081.s2020.orange.ui.views.activityDiary;


import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.ActivityEntry;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.service.ActivityEntryService;
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
public class ActivityDiaryPresenterImpl implements ActivityDiaryPresenter,
    ActivityDiaryView.Observer, HasLogger {

  private final ActivityDiaryView activityDiaryView;
  private final ActivityEntryService activityEntryService;
  private final CurrentUser currentUser;
  private final PatientService patientService;

  @Override
  public void onBeforeEnter() {
    activityDiaryView.setObserver(this);
  }

  @Override
  public void saveNewActivityEntry(ActivityEntry ae) {
    ae.setDiary(((Patient) currentUser.getUser()).getActivityDiary());
    getLogger().info("Save new activity entry [{}]", ae.toString());
    activityEntryService.saveNewActivityEntry(ae);
  }

  @Override
  public View getView() {
    return activityDiaryView;
  }
}
