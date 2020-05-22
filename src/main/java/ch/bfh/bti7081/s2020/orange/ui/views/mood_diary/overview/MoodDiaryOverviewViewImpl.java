package ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.overview;

import com.vaadin.flow.component.html.Div;
import lombok.Setter;

public class MoodDiaryOverviewViewImpl extends Div implements MoodDiaryOverviewView {

  @Setter
  Observer observer;

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
