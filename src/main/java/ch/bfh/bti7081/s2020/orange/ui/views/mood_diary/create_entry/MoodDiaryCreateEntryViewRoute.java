package ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.create_entry;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;

@Route(value = AppConst.PAGE_MOOD_DIARY_CREATE_ENTRY, layout = MainView.class)
@PageTitle(AppConst.TITLE_MOOD_DIARY)
@RequiredArgsConstructor
@PreserveOnRefresh
@Secured(Role.PATIENT)
public class MoodDiaryCreateEntryViewRoute extends VerticalLayout implements BeforeEnterObserver,
    View {

  private final MoodDiaryCreateEntryPresenter moodDiaryCreateEntryPresenter;

  @PostConstruct
  public void init() {
    add(this.getComponent(Component.class));
  }

  @Override
  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
    moodDiaryCreateEntryPresenter.onBeforeEnter();
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return moodDiaryCreateEntryPresenter.getView().getComponent(type);
  }
}
