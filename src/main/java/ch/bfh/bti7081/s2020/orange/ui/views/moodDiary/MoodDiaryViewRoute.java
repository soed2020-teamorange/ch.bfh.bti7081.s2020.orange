package ch.bfh.bti7081.s2020.orange.ui.views.moodDiary;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;

import javax.annotation.PostConstruct;

@Route(value = AppConst.PAGE_MOODDIARY, layout = MainView.class)
@RouteAlias(value = AppConst.PAGE_MOODDIARY, layout = MainView.class)
@PageTitle(AppConst.TITLE_MOODDIARY)
@RequiredArgsConstructor
@PreserveOnRefresh
@Secured(Role.PATIENT)
public class MoodDiaryViewRoute extends VerticalLayout implements BeforeEnterObserver, View {

  private final MoodDiaryPresenter moodDiaryPresenter;

  @PostConstruct
  public void init() {
    add(this.getComponent(Component.class));
  }

  @Override
  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
    moodDiaryPresenter.onBeforeEnter();
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return moodDiaryPresenter.getView().getComponent(type);
  }
}
