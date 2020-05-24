package ch.bfh.bti7081.s2020.orange.ui.views.activity_diary.create_entry;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.ActivityEntry;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientActivityDiaryService;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ActivityDiaryCreateEntryPresenterImpl implements ActivityDiaryCreateEntryPresenter,
    ActivityDiaryCreateEntryView.Observer, HasLogger {

  private final ActivityDiaryCreateEntryView activityDiaryCreateEntryView;
  private final PatientActivityDiaryService patientActivityDiaryService;

  @Override
  public void onBeforeEnter() {
    activityDiaryCreateEntryView.setObserver(this);
  }

  @Override
  public void saveNewActivityEntry(ActivityEntry ae) {
    getLogger().info("Save new activity entry [{}]", ae.toString());
    patientActivityDiaryService.addEntry(ae);
  }

  @Override
  public View getView() {
    return activityDiaryCreateEntryView;
  }
}
