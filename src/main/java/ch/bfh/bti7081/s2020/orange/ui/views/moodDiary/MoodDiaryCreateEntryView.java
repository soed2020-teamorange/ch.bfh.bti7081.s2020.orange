package ch.bfh.bti7081.s2020.orange.ui.views.moodDiary;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodEntry;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;

public interface MoodDiaryCreateEntryView extends ViewWithObserver<MoodDiaryCreateEntryView.Observer> {


  interface Observer {

    void saveNewMoodEntry(MoodEntry me);

  }
}
