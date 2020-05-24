package ch.bfh.bti7081.s2020.orange.ui.views.activity_diary.create_entry;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.ActivityEntry;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;

public interface ActivityDiaryCreateEntryView extends
    ViewWithObserver<ActivityDiaryCreateEntryView.Observer> {


  interface Observer {

    void saveNewActivityEntry(ActivityEntry me);

  }
}
