package ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.overview;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodEntry;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import java.util.List;

public interface MoodDiaryOverviewView extends View {

  void setEntries(List<MoodEntry> entries);
}
