package ch.bfh.bti7081.s2020.orange.ui.views.activity_diary.overview;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.ActivityEntry;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import java.util.List;

public interface ActivityDiaryOverviewView extends View {

  void setEntries(List<ActivityEntry> entries);
}
