package ch.bfh.bti7081.s2020.orange.ui.views.activityDiary;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.ActivityEntry;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;

public interface ActivityDiaryView extends ViewWithObserver<ActivityDiaryView.Observer> {


  interface Observer {

    void saveNewActivityEntry(ActivityEntry me);

  }
}
