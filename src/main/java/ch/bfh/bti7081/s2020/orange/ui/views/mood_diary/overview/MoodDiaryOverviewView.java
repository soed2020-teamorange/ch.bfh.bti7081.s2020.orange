package ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.overview;

import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;
import ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.overview.MoodDiaryOverviewView.Observer;

public interface MoodDiaryOverviewView extends ViewWithObserver<Observer> {

  interface Observer {

    public void onNavigateToCreate();
  }
}
