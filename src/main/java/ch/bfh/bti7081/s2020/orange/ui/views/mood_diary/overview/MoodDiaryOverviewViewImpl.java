package ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.overview;

import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.spring.annotation.UIScope;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@UIScope
@Component
@RequiredArgsConstructor
public class MoodDiaryOverviewViewImpl extends Div implements MoodDiaryOverviewView {

  @PostConstruct
  public void init() {
    Button button = new Button("Neuen Eintrag erstellen");
    button.addClickListener(
        e -> button.getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_MOOD_DIARY_CREATE_ENTRY)));

    add(button);
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
