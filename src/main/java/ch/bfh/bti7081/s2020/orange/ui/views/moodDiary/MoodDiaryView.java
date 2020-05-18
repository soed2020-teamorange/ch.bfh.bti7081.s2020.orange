package ch.bfh.bti7081.s2020.orange.ui.views.moodDiary;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodEntry;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;

import java.util.List;

public interface MoodDiaryView extends ViewWithObserver<MoodDiaryView.Observer> {


  interface Observer {

    void saveNewMoodEntry(MoodEntry me);

  }
}
